package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MultiThreadReactorServer {

    public static void start(int port) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.bind(new InetSocketAddress(port), 128);


        //注册accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new MultiThreadReactorServer.Acceptor(selector, serverSocketChannel));


        //阻塞等待就绪事件
        while (selector.select() > 0) {
            //遍历就绪事件
            for (SelectionKey selectedKey : selector.selectedKeys()) {
                selector.selectedKeys().remove(selectedKey);
                NIOHandler handler = (NIOHandler) selectedKey.attachment();
                handler.handle();
            }
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
                socketChannel.register(selector, SelectionKey.OP_READ, new MultiThreadReactorServer.DispatchHandler(socketChannel));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取数据处理
     */
    public static class DispatchHandler implements NIOHandler {


        private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() << 1);
        private SocketChannel socketChannel;

        public DispatchHandler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void handle() {
            executor.execute(new ReaderHandler(socketChannel));
        }
    }


    public static class ReaderHandler implements Runnable {
        private SocketChannel socketChannel;

        public ReaderHandler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        public void run() {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int cnt = 0, total = 0;
                String msg = "";
                do {
                    cnt = socketChannel.read(buffer);
                    if (cnt > 0) {
                        total += cnt;
                        msg += new String(buffer.array());
                    }
                    buffer.clear();
                } while (cnt >= buffer.capacity());
                System.out.println("read data num:" + total);
                System.out.println("recv msg:" + msg);

                //回写数据
                ByteBuffer sendBuf = ByteBuffer.allocate(msg.getBytes().length + 1);
                sendBuf.put(msg.getBytes());
                socketChannel.write(sendBuf);

            } catch (Exception e) {
                e.printStackTrace();
                if (socketChannel != null) {
                    try {
                        socketChannel.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        MultiThreadReactorServer.start(9999);
    }
}
