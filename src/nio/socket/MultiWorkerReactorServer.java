package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MultiWorkerReactorServer {

    private final static int PROCESS_NUM = 10;

    public static void start(int port) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.bind(new InetSocketAddress(port), 128);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Acceptor implements NIOHandler {

        private Selector selector;
        private ServerSocketChannel serverSocketChannel;

        public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
            this.selector = selector;
            this.serverSocketChannel = serverSocketChannel;
        }

        @Override
        public void handle() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ, new DispatchHanler());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取数据处理
     */
    public static class DispatchHandler {
        private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() << 1);
        private Selector selector;

        public DispatchHandler() throws IOException {
            selector = Selector.open();
            this.start();
        }


        public void addChannel(SocketChannel socketChannel) throws ClosedChannelException {
            socketChannel.register(selector, SelectionKey.OP_READ);
            this.selector.wakeup();
        }

        private void start() {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Set<SelectionKey> keys = selector.selectedKeys();
                        if (!keys.isEmpty()) {
                            Iterator<SelectionKey> iterator = keys.iterator();
                            if (iterator.hasNext()) {
                                SelectionKey key = iterator.next();
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                iterator.remove();
                                if (key.isReadable()) {
                                    try {
                                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                                        int cnt = 0, total = 0;
                                        String msg = "";
                                        do {
                                            cnt = socketChannel.read(buffer);
                                            if (cnt>0){
                                                total+=cnt;
                                                msg+=new String(buffer.array());
                                            }
                                            buffer.clear();
                                        }while (cnt>=buffer.capacity());
                                        System.out.println("read data num:"+total);
                                        System.out.println("recv msg:"+msg);

                                        //回写数据
                                        ByteBuffer snedBuf=ByteBuffer.allocate(msg.getBytes().length+1);
                                        snedBuf.put(msg.getBytes());
                                        socketChannel.write(snedBuf);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        if (socketChannel!=null){
                                            try {
                                                socketChannel.close();
                                            } catch(Exception ex){
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                                keys.remove(key);
                        }
                    }
                }
            });
        }
    }

        public static void main(String[] args) {
            MultiWorkerReactorServer.start(9999);
        }
}
