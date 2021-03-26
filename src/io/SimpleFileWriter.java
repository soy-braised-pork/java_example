package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SimpleFileWriter {
    public static void writeFileOne(String fileName,String content) throws IOException{
        File file=new File(fileName);
        OutputStream out=null;
        try{
            System.out.println("以字节为单位写入文件内容，一次写一个字节：");
            //底层是逐个写入
            out=new FileOutputStream(file);
            out.write(content.getBytes());
//            out.flush();      //fluse空方法，没用
//            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (out!=null){
                try{
                    out.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }


    public static void writeFileBuffer(String fileName,String content){
    }
}
