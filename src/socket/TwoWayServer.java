package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TwoWayServer {
    public static void main(String[] args) throws IOException {
        // 监听指定的端口
        int port = 9000;
        ServerSocket server = new ServerSocket(port);

        //server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();

        //建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream inputStream= socket.getInputStream();
        byte[] bytes=new byte[1024];
    }
}
