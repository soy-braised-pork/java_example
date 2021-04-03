package nio.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNio {
    public static void main(String[] args) {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("src/nio.txt", "rw");
            //传输Buffer数据通道
            FileChannel fileChannel = aFile.getChannel();
            //放数据的容器
            ByteBuffer buf = ByteBuffer.allocateDirect(10);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                //读写切换
                //buffer有读模式和写模式
                buf.flip();
                while (buf.position() < 3) {
                    System.out.println((char) buf.get());
                }
                System.out.println();
                buf.compact();
                bytesRead = fileChannel.read(buf);
                System.out.println(bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
