package designmode;

import java.util.ArrayList;

/**
 * 访问者
 */

interface Visitor {
    void visit(Com com);
}

abstract class Com {
    private String name;
    protected Visitor visitor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Com(Visitor visitor) {
        this.visitor = visitor;
    }

    private ArrayList<Com> coms = new ArrayList<>();

    public ArrayList<Com> getComs() {
        return coms;
    }

    public void add(Com com) {
        this.coms.add(com);
    }

    public abstract void accept();
}

class Visitor1 implements Visitor {
    @Override
    public void visit(Com com) {
        System.out.println("visitor1:" + com.getName());
        for (Com c : com.getComs()) {
            c.accept();
        }
    }
}

class Visitor2 implements Visitor {

    @Override
    public void visit(Com com) {
        System.out.println("visitor2:" + com.getName());
    }
}

class Com1 extends Com {

    public Com1(Visitor visitor) {
        super(visitor);
    }

    @Override
    public void accept() {
        super.visitor.visit(this);
    }
}

public class VisitorTest {
    public static void main(String[] args) {
        Visitor visitor1 = new Visitor1();
        Visitor visitor2 = new Visitor2();

        Com com1 = new Com1(visitor1);
        com1.setName("com1");

        Com com2 = new Com1(visitor1);
        com2.setName("com2");

        Com com3 = new Com1(visitor2);
        com3.setName("com3");

        com1.add(com3);
        com2.add(com1);
        com3.add(com2);
        com1.accept();
    }


}