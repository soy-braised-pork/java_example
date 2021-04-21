package thread;

//继承Runnable接口，重写run()方法
public class Thread2 implements Runnable {


    @Override
    public void run() {
        System.out.println("2222");
    }
}
