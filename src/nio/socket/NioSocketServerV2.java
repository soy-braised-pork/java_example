package nio.socket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

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
                //如果selectionkey对应的通道包含客户端的连接请求
                if (selectionKey.isAcceptable()) {
                    //调用accept方法建立连接，产生服务器端对应的SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置采用非阻塞模式
                    socketChannel.configureBlocking(false);
                    //将该SocketChannel也注册到Selector
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                //如果selectionKey对应的通道有数据需要读取
                if (selectionKey.isReadable()) {
                    //获取该SelectionKey对应的Channel，该Channel中有可读的数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //定义准备执行读取数据的ByteBuffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    StringBuilder content = new StringBuilder();
                    //开始读数据
                    try {
                        while (socketChannel.read(byteBuffer) > 0) {
                            byteBuffer.flip();
                            content.append(StandardCharsets.UTF_8.decode(byteBuffer));
                        }
                        //打印从该sk对应的Channel里读到的数据
                        System.out.println("====" + content);
                    }
                    //如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                    //  对应的Client出现了问题，所以从Selector中取消SelectionKey的注册
                    catch (IOException ex) {
                        //从Selector中删除指定的SelectionKey
                        selectionKey.cancel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                        //如果content的长度大于0，即聊天信息不为空
                        if (content.length() > 0) {
                            //遍历该selector里注册的所有SelectionKey
                            for (SelectionKey selectionKey1 : selector.keys()) {
                                //获取该key对应的Channel
                                Channel targetChannel = selectionKey.channel();
                                //如果该Channel是SocketChannel对象
                                if (targetChannel instanceof SocketChannel) {
                                    //将读到的内容写入该Channel中
                                    SocketChannel dest = (SocketChannel) targetChannel;
                                    dest.write(StandardCharsets.UTF_8.encode(content.toString()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

