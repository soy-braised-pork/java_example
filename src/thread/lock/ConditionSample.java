package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSample implements Runnable{

    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition= lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread going");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
      ReentrantLockSample r1=new ReentrantLockSample();
      Thread t1=new Thread(r1);
      t1.start();
      Thread.sleep(2000);
      lock.lock();
      //....
        condition.signal();
        lock.unlock();
    }
}
