package designmode;

import java.util.ArrayList;

/**
 * 组合模式
 *
 * 树形
 */

abstract class Component {

    //文件路径
    abstract public void printlocal(String perfix);

    public void add(Component composite) throws Exception {
        throw new Exception("文件不能加文件");
    }

    public void remove(Component composite) throws Exception{
         throw new Exception("文件不能刪文件");
    }
}

/*
文件类
 */
class MyFile extends Component {
    private String name;

    public MyFile(String name) {
        this.name = name;
    }

    @Override
    public void printlocal(String perfix) {
        System.out.println(perfix + "/" + this.name);
    }


}

/*
文件夹类
 */
class MyFolder extends Component {
    private ArrayList<Component> arrayList = new ArrayList<>();
    private String name;

    public MyFolder(String name) {
        this.name = name;
    }

    @Override
    public void printlocal(String perfix) {
        perfix = perfix + "/" + this.name;
        System.out.println(perfix);
        for (Component component : arrayList) {
            component.printlocal(perfix);
        }
    }

    @Override
    public void remove(Component component) {
        this.arrayList.remove(component);
    }

    @Override
    public void add(Component component) {
        this.arrayList.add(component);
    }
}

public class ComponentTest {
    public static void main(String[] args) throws Exception {
        MyFolder myFolder1 = new MyFolder("a1");
        MyFolder myFolder2 = new MyFolder("a2");
        MyFolder myFolder3 = new MyFolder("a3");
        MyFile myFile1 = new MyFile("f1");
        MyFile myFile2 = new MyFile("f2");

        myFolder1.add(myFolder2);
//        myFile1.add(myFile2);   文件不能加文件
        myFolder2.add(myFolder3);
        myFolder3.add(myFile2);
//        myFile1.remove(myFile1);  異常
        myFolder1.remove(myFolder2);
        myFolder1.printlocal("/");
    }
}