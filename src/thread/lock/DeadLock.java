package thread.lock;

/**
 * 死锁
 * <p>
 * 线程A通过synchronized(resource1)获得resource1的监视器锁，
 * 然后通过 Thread.sleep(1000); 让线程休眠1s，为的是让线程B得到执行然后获取到resource2的监视器锁
 * 线程A、B休眠结束都开始企图请求获取对方的资源，然后这两个线程就会陷入互相等待的状态，产生死锁
 * <p>
 * 产生死锁的四个必要条件
 * 1、互斥条件：该资源任意一个时刻只由一个线程占用
 * 2、请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放
 * 3、不剥夺条件：线程已获得的资源在未使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源
 * 4、循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系
 */


public class DeadLock {
    private static Object resource1 = new Object();  //资源1
    private static Object resource2 = new Object();  //资源2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
            }
        }, "线程2").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程2").start();
    }
}
