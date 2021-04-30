package designmode;

class test {
    public boolean connection() {
        return true;
    }


    public boolean closed(){
        return true;
    }
}

class driver{

}


public class Order {
    public static void main(String[] args) {
        test test=new test();
        if (test.connection()) {
            System.out.println("设备已连接。。。。");
        }else {
            System.out.println("设备已断开。。。。");
        }
    }
}
