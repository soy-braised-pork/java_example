package nio.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileStreamIO {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            in=new BufferedInputStream(new FileInputStream("src/nomal_io.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
