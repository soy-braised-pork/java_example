package nio.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsyncFile {
    public static void main(String[] args) throws IOException {
        try (
            AsynchronousFileChannel inChannel = AsynchronousFileChannel.open(
                    Paths.get("src/nio.txt"), StandardOpenOption.READ);) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //read的第二个参数指定了channel的起始位置
                Future<Integer> result = inChannel.read(buffer, 0);
                //一直轮询I/O操作是否完成
                int i = 0;
                while (!result.isDone()) {
                    //做点别的
                    System.out.println("do other things" + i);
                    i++;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
            }
        }
    }
