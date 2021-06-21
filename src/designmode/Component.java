package designmode;

import java.util.ArrayList;

/**
 * 组合模式
 * <p>
 * 树形
 */


abstract class Component {
    abstract public void printlocal(String perfix);

    public void add(Component com) throws Exception {
        throw new Exception("文件不能加文件！");
    }
}

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

class MyDir extends Component {

    private ArrayList<Component> sub = new ArrayList<>();
    private String name;

    public MyDir(String name){
        this.name=name;
    }

    @Override
    public void printlocal(String perfix) {
        perfix = perfix + "/" + this.name;
        System.out.println(perfix);
        for (Component c:sub){
            c.printlocal(perfix);
        }
    }
    @Override
    public void add(Component com) {
        this.sub.add(com);
    }
}

public class Component {
    public static void main(String[] args) throws Exception {
        MyDir m1 = new MyDir("a1");
        MyDir m2 = new MyDir("a2");
        MyDir m3 = new MyDir("a3");
        MyFile f1 = new MyFile("f1");
        MyFile f2 = new MyFile("f2");
//        MyFile f3=new MyFile("f3");
        m1.add(m2);
        m1.add(f1);
        m2.add(m3);
        m3.add(f2);
//        f2.add(f3);
        m1.printlocal("/");
    }
}
