package designmode;

import java.util.ArrayList;

abstract class RDD {
    private RDD next = null;

    public RDD addnext(RDD rdd) {
        this.next = rdd;
        return rdd;
    }

    abstract public boolean diot();

    public void exec() {
        RDD temp = this;
        while (temp != null) {
            boolean b = temp.diot();
            if (!b) {
                break;
            }
            temp = temp.next;
        }
    }
}

class RDD1 extends RDD {
    private String name;

    public RDD1(String name) {
        this.name = name;
    }

    @Override
    public boolean diot() {
        System.out.println(this.name + "处理不了");
        return true;
    }
}

class RDD2 extends RDD {
    private String name;

    public RDD2(String name) {
        this.name = name;
    }

    @Override
    public boolean diot() {
        System.out.println(this.name + "处理了");
        return false;
    }
}

public class ChainOfResponsibility {
    public static void main(String[] args) {
        RDD rdd1=new RDD1("r1");
        RDD rdd2=new RDD2("r2");
        RDD rdd3=new RDD1("r3");
        RDD rdd4=new RDD1("r4");
        rdd1.addnext(rdd2).addnext(rdd3).addnext(rdd4);
        rdd1.exec();
    }
}
