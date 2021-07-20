package designmodemy;

//电脑品牌接口
interface Brand {
    void sale();
}

//创建电脑品牌
class Lenovo implements Brand {
    @Override
    public void sale() {
        System.out.println("联想");
    }
}

class Dell implements Brand {
    @Override
    public void sale() {
        System.out.println("戴尔");
    }
}

//电脑集成品牌
class Computer {
    protected Brand brand;

    public Computer(Brand b) {
        this.brand=b;
    }
    public void sale(){
        brand.sale();
    }
}

//
class Desktop extends Computer{
    public Desktop(Brand b) {
        super(b);
    }
    @Override
    public void sale(){
        super.sale();
        System.out.println("出售台式电脑");
    }
}

class Laptop extends Computer{
    public Laptop(Brand b) {
        super(b);
    }
    @Override
    public void sale(){
        super.sale();
        System.out.println("出售笔记本");
    }
}

public class Bridging {
    public static void main(String[] args) {
        Computer computer=new Laptop(new Lenovo());
        computer.sale();
        Computer computer1=new Desktop(new Dell());
        computer1.sale();
    }
}