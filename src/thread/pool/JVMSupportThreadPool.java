package thread.pool;

import javafx.concurrent.ScheduledService;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JVMSupportThreadPool {
    public static void main(String[] args) {

        //四大线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5);


        TestPoolRunnable testPoolRunnable = new TestPoolRunnable();

//        //顺序执行，十次循环创建十个线程，运行完不退出
//        for (int i = 0; i < 10; i++) {
//            singleThreadExecutor.submit(testPoolRunnable);
//        }
//        //结束
//        singleThreadExecutor.shutdown();


//        //并发执行---无序
//        for (int i=0;i<10;i++){
//            newCachedThreadPool.submit(testPoolRunnable);
//        }
//        newCachedThreadPool.shutdown();



//        for (int i=0;i<10;i++){
//            newFixedThreadPool.submit(testPoolRunnable);
//        }
//        newFixedThreadPool.shutdown();



        //设置等待时间
        for (int i=0;i<10;i++){
            //2s之内
            //固定延迟：任务之间的时间间隔，也就是说当上一个任务执行完成后，我会在图定延迟时间后出发第二次任务。注重距上次完成任务后的时间间隔。
            newScheduledThreadPool.scheduleWithFixedDelay(testPoolRunnable,0,2, TimeUnit.SECONDS);
            //每隔2s
            // 固定频率：每间隔固定时间就执行一次任务。注重频率。
            newScheduledThreadPool.scheduleAtFixedRate(testPoolRunnable,0,2,TimeUnit.SECONDS);
            newScheduledThreadPool.submit(testPoolRunnable);
        }

        newScheduledThreadPool.shutdown();
    }
}
