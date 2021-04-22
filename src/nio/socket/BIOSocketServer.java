package nio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * BIO：
 * 同步阻塞式IO，
 * 服务器实现模式为一个连接一个线程，
 * 即客户端有连接请求时服务端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善。
 */


/**
 * BIO处理网络连接有两部分
 * 1、ServerSocket 服务器
 * 2、Socket 客户端
 */

public class BIOSocketServer {
    public static void server() {
        ServerSocket serverSocket = null;
        InputStream in = null;
        try {
            serverSocket = new ServerSocket(10010);

            int recvMsgSzie = 0;

            byte[] recvBuf = new byte[1024];
            while (true) {
                /*
                单线程：效率低
                解决方法：多线程，但是开线程也会耗CPU性能，
                                线程越多所能分配的时间片就越少，CPU开始轮转，每个线程切换时都有开销
                         ！！！！！nio
                 */
                //当客户端向服务端发起连接，服务端的ServerSocket就可以通过accept方法，拿到客户端连接
                Socket clntSocket = serverSocket.accept();  //监听，阻塞
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client at" + clientAddress);
                in = clntSocket.getInputStream();
                while ((recvMsgSzie = in.read(recvBuf)) != -1) {
                    byte[] temp = new byte[recvMsgSzie];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSzie);
                    System.out.println(new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        server();
    }
}


















