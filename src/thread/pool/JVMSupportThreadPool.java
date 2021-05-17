package thread.pool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JVMSupportThreadPool {
    public static void main(String[] args) {

        //核心线程数为1，最大值1
        //单线程的线程池。若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，
        //待线程空闲，按先入先出的顺序执行队列中的任务。
        /*单一线程，对同步要求较高使用*/
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        //核心线程数为0，最大值Integer.MAX_VALUE
        //不确定长度的线程池。但若有空闲线程可以复用，则会优先使用可复用的线程。
        // 若所有线程均在工作，又有新任务提交，则会创建新线程处理任务。所有线程在当前任务执行完毕后，将返回线程池进行复用。
        /*全自动化*/
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //核心线程数、最大值可自行设置
        //定长线程池。当有一个新任务提交时，线程池中若有空闲线程，则立即执行。
        //若没有，则新任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务。
        /*没有非核心线程的线程池*/
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

        //核心线程数为自行设置，最大值Integer.MAX_VALUE
        //周期性、定时执行任务的线程池
        /*纯手动*/
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
