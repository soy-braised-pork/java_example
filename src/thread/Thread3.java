package thread;

/**
 *锁：
 * 1、原子性
 * 2、可见性
 * 3、指令重排
 */

public class Thread3 implements Runnable{
    int a=0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(a++);
        }
    }

    public static void main(String[] args) {
        Thread3 thread3 = new Thread3();
            new Thread(thread3).start();
            new Thread(thread3).start();
    }


}
