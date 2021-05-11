package nio.socket;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class MultiWorkerReactorServer {

    private final static int PROCESS_NUM=10;

    public static void start(int port){
        try {
            Selector selector=Selector.open();
            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.bind(new InetSocketAddress(port),128);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
