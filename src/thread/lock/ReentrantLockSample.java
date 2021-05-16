package thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSample implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            lock.lock();
//            lock.lockInterruptibly();
//            lock.tryLock(1, TimeUnit.SECONDS);
            try {
                //一个int值，在多线程下做自增操作，是线程不安全的
                //解决方法：1、加锁  2、AtomicInteger
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockSample lockSample = new ReentrantLockSample();
        Thread t1 = new Thread(lockSample);
        Thread t2 = new Thread(lockSample);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
