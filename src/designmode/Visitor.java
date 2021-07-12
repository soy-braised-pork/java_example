package designmode;

import java.util.ArrayList;

/**
 * 访问者
 */

interface IVisitor {
    void visit(AbsCom com);
}


class Visitor1 implements IVisitor {
    @Override
    public void visit(AbsCom com) {
        System.out.println("visit1:" + com.getName());
        for (AbsCom c : com.getAbsComs()) {
            c.accept();
        }
    }
}

class Visitor2 implements IVisitor{
    @Override
    public void visit(AbsCom com) {
        System.out.println("visit2:"+com.getName());
    }
}


abstract class AbsCom {
    private String name;
    protected IVisitor visitor;
    private ArrayList<AbsCom> absComs = new ArrayList<>();

    public AbsCom(IVisitor visitor) {
        this.visitor = visitor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AbsCom> getAbsComs() {
        return absComs;
    }

    public void add(AbsCom com) {
        this.absComs.add(com);
    }

    abstract public void accept();
}

class Com1 extends AbsCom {
    public Com1(IVisitor visitor) {
        super(visitor);
    }

    @Override
    public void accept() {
        super.visitor.visit(this);
    }
}

public class Visitor {
    public static void main(String[] args) {
        IVisitor visitor1 = new Visitor1();
        IVisitor visitor2 = new Visitor2();
        AbsCom com1=new Com1(visitor1);
        com1.setName("com1");
        AbsCom com2=new Com1(visitor1);
        com2.setName("com2");
        AbsCom com3=new Com1(visitor2);
        com3.setName("com3");
        AbsCom com4=new Com1(visitor1);
        com4.setName("com4");
        com1.add(com2);
        com2.add(com3);
        com3.add(com4);
        com1.accept();
    }

}