package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NioSocketClientV2 {

    //定义检测SocketChannel的Selector对象
    private Selector selector=null;
    //客户端SocketChannel
    private SocketChannel sc=null;

    public void init() throws IOException {
        selector=Selector.open();
        InetSocketAddress isa=new InetSocketAddress("127.0.0.1",30000);

    }
}
