package designmode;

interface Brand {
    void sale();
}

// 创建电脑牌子
class Lenovo implements Brand {
    @Override
    public void sale() {
        System.out.println("联想！！");
    }
}

class Dell implements Brand {
    @Override
    public void sale() {
        System.out.println("戴尔!!");
    }
}

class Computer {
    protected Brand brand;

    public Computer(Brand b) {
        this.brand = b;
    }

    public void sale() {
        brand.sale();
    }
}

class Desktop extends Computer {
    public Desktop(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("出售台式电脑");
    }
}

class Laptop extends Computer {
    public Laptop(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("出售笔记本");
    }
}


public class bridging {
    public static void main(String[] args) {
        Computer c = new Laptop(new Lenovo());
        c.sale();
        Computer c2=new Desktop(new Dell());
        c2.sale();
    }
}
