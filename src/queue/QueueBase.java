package queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class QueueBase {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---------------ArrayBlockingQueue--------------");
        //阻塞队列  有长度的队列
        ArrayBlockingQueue<String> array = new ArrayBlockingQueue<>(5);
        array.put("a");
        array.put("b");
        array.add("c");
        array.add("d");
        array.add("e");
        //返回一个布尔类型 在3秒之内能不能加入 不能返回false
        System.out.println(array.offer("a", 3, TimeUnit.SECONDS));
        System.out.println("所有数据  >>  " + array.toString());


        System.out.println("---------------LinkedBlockingQueue---------------");
        //阻塞队列  无长度限制队列
        LinkedBlockingQueue<String> linked = new LinkedBlockingQueue<>();
        linked.offer("a");
        linked.offer("b");
        linked.offer("c");
        linked.offer("d");
        linked.add("f");
        System.out.println("总长度 >> " + array.size());
        for (Iterator iterator = linked.iterator(); iterator.hasNext(); ) {
            String string = (String) iterator.next();
            System.out.println(string + "--");
        }
        System.out.println();
        List<String> list = new ArrayList<>();
        //在q的队列中取三个元素放到list队列里
        System.out.println(linked.drainTo(list, 3));
        System.out.println("取出LinkedBlockingQueue数据放到list列表的长度为 >> " + list.size());
        for (String string : list) {
            System.out.println(string + "--");
        }
        System.out.println();


        System.out.println("---------------SynchronousQueue---------------");
        System.currentTimeMillis();
        final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "取数据" + synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronousQueue.add("b");
                System.out.println(Thread.currentThread().getName() + "加入数据");
            }
        });
        thread1.start();
    }
}
