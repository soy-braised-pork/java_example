package thread.lock;

public class Thread4 implements Runnable {

     static int a=0;
    @Override
    public  void run() {
        for (int i = 0; i < 1000000; i++) {
            a++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread4 thread4=new Thread4();
        Thread thread = new Thread(thread4);
        Thread thread1 = new Thread(thread4);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(a);

    }
}
