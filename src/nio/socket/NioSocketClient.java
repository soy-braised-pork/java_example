package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioSocketClient {
    public static void client() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 10010));
    }
}
