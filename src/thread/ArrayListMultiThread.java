package thread;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



public class ArrayListMultiThread {
    //效率低下
//    static Vector<Integer> list = new Vector<>();

    static List<Integer> list = new ArrayList<>();

    public static class AddThread implements Runnable{

        @Override
        public void run() {
           // 锁粒度：锁细不锁粗
//            synchronized(list){
            for (int i=0;i<1000000;i++){
                synchronized (list){
                    list.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(new AddThread());
        Thread t2=new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());

    }

}
