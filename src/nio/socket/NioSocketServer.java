package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO：
 * 同步非阻塞式IO，服务器实现模式为一个请求一个线程，
 * 即客户端发送连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。
 */

/**
 * 局限性
 * 从始至终只使用了一个线程，多线程可发挥多核CPU优势
 * 代码复杂度高
 * 优化：selectionKey可再绑定attach----回调
 */

public class NioSocketServer {
    //缓冲池大小
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 10010;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        selector();
    }

    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }


    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = sc.read(buf);
        while (bytesRead > 0) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.println();
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if (bytesRead == -1) {
            sc.close();
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }


    public static void selector() {
        //初始化一个selector对象
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try {
            //调用selector的open方法表示创建一个selector出来
            selector = Selector.open();
            //创建ServerSocketChannel
            ssc = ServerSocketChannel.open();
            //socket绑定端口
            ssc.socket().bind(new InetSocketAddress(PORT));
            //设置为非阻塞模式
            /*selector只接受费阻塞模式的Channel注册*/
            ssc.configureBlocking(false);
            //往selector上注册事件
            /*
            * 事件：OP_ACCEPT     可以接收相关的数据
            *      OP_CONNECT    连接就绪状态
            *      OP_READ       读就绪--->当前Channel有数据可读
            *      OP_WRITE      写就绪--->等待写数据 */
            //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
            ssc.register(selector,SelectionKey.OP_ACCEPT);
            //不断轮循,监听selector上是否有需要处理的事件，如果有，则进行处理
            while (true){
                //每隔3s，释放一次selector
                if (selector.select(TIMEOUT)==0){
                    System.out.println("==");
                    continue;
                }
                // 获得selector中选中的项的迭代器，选中的项为注册的事件
                Iterator<SelectionKey> iter=selector.selectedKeys().iterator();
                while (iter.hasNext()){
                    SelectionKey key=iter.next();
                    // 客户端请求连接事件
                    if (key.isAcceptable()){
                        handleAccept(key);
                    }
                    // 获得了可读的事件
                    if (key.isReadable()){
                        handleRead(key);
                    }
                    if (key.isWritable()&& key.isValid()){
                        handleWrite(key);
                    }
                    if (key.isConnectable()){
                        System.out.println("isConnectable=true");
                    }
                    // 删除已选的key,以防重复处理
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (selector!=null) {
                    selector.close();
                }
                if (ssc!=null){
                    ssc.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}

