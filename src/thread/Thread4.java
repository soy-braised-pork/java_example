package thread;

class t1 extends Thread{
    @Override
    public void run() {
        System.out.println("t1 开始了");
    }
}

class t2 implements Runnable{

    @Override
    public void run() {
        System.out.println("t2 开始了");
    }
}

public class Thread4 {
    public static void main(String[] args) {
        t1 t1 = new t1();
        t2 t2 = new t2();

        t1.run();

        t2.run();

    }
}
