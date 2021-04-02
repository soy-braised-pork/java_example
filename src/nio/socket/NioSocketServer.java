package nio.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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
        ServerSocketChannel ssChannel=(ServerSocketChannel) key.channel();
        SocketChannel sc=ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }


    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc= (SocketChannel) key.channel();
        ByteBuffer buf= (ByteBuffer) key.attachment();
        long bytesRead= sc.read(buf);
        while (bytesRead>0){
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println();
            }
            System.out.println();
            buf.clear();
            bytesRead= sc.read(buf);
        }
        if (bytesRead==-1){
            sc.close();
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf= (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc= (SocketChannel) key.channel();
        while (buf.hasRemaining()){
            sc.write(buf);
        }
        buf.compact();
    }


    public static void selector(){
        //初始化一个selector对象
        Selector selector=null;
        ServerSocketChannel ssc=null;
        try {
            //调用selector的open方法表示创建一个selector出来
            selector=Selector.open();
            //创建ServerSocketChannel
            ssc=ServerSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

