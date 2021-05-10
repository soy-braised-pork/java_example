package nio.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class NewFileOp {
    public static void main(String[] args) throws IOException {


        Path dir0=Paths.get("/home"); //创建一共Path路径；Paths是工具类，用于产生Path
        Files.createDirectory(dir0);   //创建单级目录；若已存在，则抛出异常
        Path dir1=Paths.get("/home/zhulixin/workspace/test1/test2/test.txt");
        Files.createDirectory(dir1);  //创建文件；若目录没有，则抛出异常
        Path dir2=Paths.get("/home/zhulixin/workspace/test1/test2/test1.txt");
        Files.createDirectory(dir2);   //创建多级目录；若已存在，不抛出异常

        Path dir3=Paths.get("/home/zhulixin/workspace/test");
        //1、DirectoryStream可以理解成对于Path的Iterable，返回在dir3中符合第二个参数形式的Path集合
        //2、下面用到了一种try-with-resources的写法，该写法是在java1.7中引入的语法（！）

        Path source = Paths.get("/home/zhulixin/workspace/test1/test2/test.txt");
        Path target = Paths.get("/home/zhulixin/workspace/test1/test2/test1.txt");

        //以防乱码最好设置一下文件的编码格式，在StandardCharsets中有很多种内置编码格式
        List<String> list = Files.readAllLines(source, StandardCharsets.UTF_8);
        for (String s : list){
            System.out.println(s);
        }

        //在StandardOpenOption中有很多种内置文件打开方式可供选择
        List<String> writeList=new ArrayList<>();
        writeList.add("111111111");
        writeList.add("222222222");
        writeList.add("333333333");

        Files.write(target,list,StandardCharsets.UTF_8, StandardOpenOption.WRITE);

    }
}
