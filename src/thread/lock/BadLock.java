package thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁：保证线程安全性
 * 用法：
 * 1、指定加锁对象：表示进入加锁的代码块后，需要获取指定对象的锁。
 * 2、用于某个实例方法（通过对象可以调用）：相当于对该实例加锁，调用这个方法之前必须获得该实例的锁。
 * 3、用于某个静态方法（static 通过类可调用）：相当于给这个类加锁，调用类中的方法，使用类中的属性，都必须获得这个类的锁
 */


public class BadLock implements Runnable {

    public static Integer i = 0;

    private static BadLock badLock = new BadLock();

//    ReentrantLock

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            synchronized (badLock) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //两个线程共用一个变量，synchronized
        Thread t1 = new Thread(badLock);
        Thread t2 = new Thread(badLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
