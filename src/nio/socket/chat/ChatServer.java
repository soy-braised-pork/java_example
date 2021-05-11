package nio.socket.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * 用nio实现一个聊天服务器
 */

public class ChatServer {

    //用于检测所有Channel状态的Selector
    private Selector selector = null;

    private Map<String, SocketChannel> serverMap = new ConcurrentHashMap<>();

    private LongAdder count = new LongAdder();

    private static final int TIMEOUT = 3000;

    public void init() throws IOException {
        selector = Selector.open();
        //通过open方法来打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 3000);
        //将该ServerSocketChannel绑定到指定IP地址
        server.socket().bind(isa);
        //设置ServerSocket以非阻塞方式工作
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(TIMEOUT) == 0) {
                System.out.println("==");
                continue;
            }
            for (SelectionKey selectionKey : selector.selectedKeys()) {
                selector.selectedKeys().remove(selectionKey);
                if (selectionKey.isAcceptable()) {
                    //调用accept方法接受连接，产生服务端对应的SocketChannel
                    SocketChannel sc = server.accept();
                    saveAndRegisterChannel(sc);
                }
                if (selectionKey.isReadable()) {
                    try {
                        ReadAndSendMessageToSelectChannel((SocketChannel) selectionKey.channel());
                    } catch (IOException e) {
                        //如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                        //对应的Client出现了问题，所以从Selector中取消sk的注册
                        //从Selector中删除指定的SelectionKey
                        selectionKey.channel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }
                }
            }
        }
    }


    private void saveAndRegisterChannel(SocketChannel socketChannel) throws IOException {
        if (socketChannel == null) {
            return;
        }
        //设置采用非阻塞模式
        socketChannel.configureBlocking(false);
        //将该SocketChannel也注册到Selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        count.increment();
        String currentId = count.toString();
        serverMap.put(currentId, socketChannel);
        for (SocketChannel channel : serverMap.values()) {
            channel.write(ByteBuffer.wrap(currentId.getBytes()));
        }
    }

    private void ReadAndSendMessageToSelectChannel(SocketChannel socketChannel) throws IOException {
        //开始读取数据
        //定义准备执行读取数据的ByteBuffer
        ByteBuffer buff = ByteBuffer.allocate(1024);
        StringBuilder content = new StringBuilder();

        //写buffer
        while (socketChannel.read(buff)>0){
            buff.flip();
            content.append(StandardCharsets.UTF_8.decode(buff));
        }
        //打印从该sk对应的Channel里读取的数据
        System.out.println("===="+content);
        //如果content的长度大于0，即聊天信息不为空
        if (content.length()>0){
            String[] split=content.toString().split("#");
            if (split.length<2){
                socketChannel.write(StandardCharsets.UTF_8.encode("格式错误"));
                return;
            }
            SocketChannel target=serverMap.get(split[0]);
            if (target==null){
                socketChannel.write(StandardCharsets.UTF_8.encode("该账号已离线0"));
                return;
            }
            try {
                target.write(StandardCharsets.UTF_8.encode(split[1]));
            }catch (IOException e){
                socketChannel.write(StandardCharsets.UTF_8.encode("该账号已离线1"));
                serverMap.remove(split[0]);
            }

//            //遍历该Selector里注册的所有SelectKey
//            for (SelectionKey key:selector.keys()){
//                //获取该key对应的Channel
//                Channel targetChannel= key.channel();
//                //如果该Channel是SocketChannel对象
//                if (targetChannel instanceof SocketChannel){
//                    //将读到的内容写入该Channel中
//                    SocketChannel dest= (SocketChannel) targetChannel;
//                }
//            }
        }
    }


    public static void main(String[] args) throws IOException {
        new ChatServer().init();
    }
}
