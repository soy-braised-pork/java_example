package thread.lock;

/**
 * 锁：
 * 1、原子性
 * 2、可见性
 * 3、指令重排
 */

public class Thread3 {

    volatile boolean running = false;

    boolean get() {
        return running;
    }

    void doSetTrue() {
        running = true;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread3 instance = new Thread3();

        new Thread(
                () -> {
                    while (!instance.get()) {
                    }

                    System.out.println("Thread 1 finished.");
                }).start();

        Thread.sleep(100);

        new Thread(
                () -> {
                    instance.doSetTrue();
                    System.out.println("Thread 2 finished.");
                }).start();
    }

}
