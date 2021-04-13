package thread.pool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JVMSupportThreadPool {
    public static void main(String[] args) {

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5);


        TestPoolRunnable testPoolRunnable = new TestPoolRunnable();

        //顺序执行，十次循环创建十个线程，运行完不退出
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.submit(testPoolRunnable);
        }
        //结束
        singleThreadExecutor.shutdown();

        //并发执行----无序
//        for (int i = 0; i < 10; i++) {
//            newCachedThreadPool.submit(testPoolRunnable);
//        }
//        newCachedThreadPool.shutdown();
//
//
//        for (int i = 0; i < 10; i++) {
//            newFixedThreadPool.submit(testPoolRunnable);
//        }
//
//        //设置等待时间
//        for (int i = 0; i < 10; i++) {
//            //2s之内
//            newScheduledThreadPool.scheduleWithFixedDelay(testPoolRunnable, 0, 2, TimeUnit.SECONDS);
//            //每隔2s
//            newScheduledThreadPool.scheduleAtFixedRate(testPoolRunnable,0,2,TimeUnit.SECONDS);
//            newScheduledThreadPool.submit(testPoolRunnable);
//        }
//
//        //TODO:Explain it
//        try {
//            newScheduledThreadPool.shutdown();
//            if (newScheduledThreadPool.awaitTermination(1,TimeUnit.SECONDS)){
//                newScheduledThreadPool.shutdown();
//            }
//        } catch (InterruptedException e) {
//            System.out.println("awaitTermination interrupted:"+e);
//            newScheduledThreadPool.shutdownNow();
//        }
    }
}
