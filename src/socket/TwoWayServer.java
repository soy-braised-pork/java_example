package socket;

import java.io.IOException;
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

    }
}
