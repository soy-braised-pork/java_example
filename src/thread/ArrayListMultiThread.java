package thread;

import kotlin.jvm.Synchronized;

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
           // Synchronized(list){
            for (int i=0;i<1000000;i++){
                synchronized (list){
                    list.add(i);
                }
            }
        }
    }


}
