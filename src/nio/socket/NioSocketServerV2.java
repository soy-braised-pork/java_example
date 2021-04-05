package nio.socket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 群聊简单模型
 */

public class NioSocketServerV2 {

    //用于检测所有Channel状态的Selector
    private Selector selector = null;

    public void init() throws IOException {
        selector = Selector.open();
        //通过open方法来打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 3000);
        //将该ServerSocketChannel绑定到指定IP地址
        serverSocketChannel.socket().bind(inetSocketAddress);
        //设置ServerSocket以非阻塞方式工作
        serverSocketChannel.configureBlocking(false);
        //将server注册到指定的Selector对象
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            //依次处理Selector上的每个已选择的SelectorKey
            for (SelectionKey selectionKey : selector.selectedKeys()) {
                //从Selector上的已选择Key集中删除正在处理的SelectionKey
                selector.selectedKeys().remove(selectionKey);
            }


        }
    }
}

