package thread;

//线程等待和通知

public class WaitAndNodify {

    static final Object obj = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            //锁
            //所有wait、notify都是建立在加锁的基础上
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "---->T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + "---->T1 wait");
                    obj.wait();//进入等待状态，释放锁
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "---->T1 end");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "---->T2 start");
                obj.notify();//调用，等待结束
                //唤醒持有这个对象的其他线程
                //当2唤醒1后，锁回到1身上
//                obj.notifyAll();
                System.out.println(System.currentTimeMillis() + "---->T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();

    }
}
