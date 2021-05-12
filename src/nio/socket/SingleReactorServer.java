package nio.socket;

import com.sun.corba.se.pept.transport.Acceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;


public class SingleReactorServer {

    public static void start(int port) throws IOException {
        Selector selector=Selector.open();
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.bind(new InetSocketAddress(port),128);

        //注册accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT,new Acceptor(selector,serverSocketChannel));

        //阻塞等待就绪事件
        while (selector.select()>0){
            //遍历就绪事件
            for (SelectionKey selectedKey:selector.selectedKeys()){
                selector.selectedKeys().remove(selectedKey);
                NIOHandler handler= (NIOHandler) selectedKey.attachment();
                handler.handle();
            }
        }
    }

    public static class Acceptor implements NIOHandler{

        private Selector selector;
        private ServerSocketChannel serverSocketChannel;

        public Acceptor(Selector selector,ServerSocketChannel serverSocketChannel){
            this.selector=selector;
            this.serverSocketChannel=serverSocketChannel;
        }

        @Override
        public void handle() {
            try {
                SocketChannel socketChannel=serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ,new DispatchHandler(socketChannel));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取数据处理
     */
    public static class DispatchHandler implements NIOHandler{

        private SocketChannel socketChannel;

        public DispatchHandler(SocketChannel socketChannel){
            this.socketChannel=socketChannel;
        }

        @Override
        public void handle() {
            try {
                ByteBuffer buffer=ByteBuffer.allocate(1024);
                int cnt,total=0;
                StringBuilder msg=new StringBuilder();
                do {
                    cnt=socketChannel.read(buffer);
                    if (cnt>0){
                        total+=cnt;
                        msg.append(new String(buffer.array()));
                    }
                    buffer.clear();
                }while (cnt>=buffer.capacity());
                System.out.println("read data num:"+total);
                System.out.println("recv msg:"+msg);

                //回写数据
                ByteBuffer snedBuf=ByteBuffer.allocate(msg.toString().getBytes().length+1);
                snedBuf.put(msg.toString().getBytes());
                socketChannel.write(snedBuf);
            } catch (Exception e) {
                e.printStackTrace();
                if (socketChannel!=null){
                    try {
                        socketChannel.close();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SingleReactorServer.start(9999);
    }
}


