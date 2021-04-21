package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Thread3 implements Callable<Integer> {


    //重写callable接口中的call()方法
    @Override
    public Integer call() {
        int i = 0;
        for (; i < 10; i++) {
            //Thread.currentThread() 得到的就是当前thread对象
            //然后在调用这个thread的 getName()方法。
            System.out.println(Thread.currentThread().getName() + "" + i);
        }
        return i;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //新建一个Callable实例
        Thread3 thread3 = new Thread3();
        //用FutureTask实例包装它
        FutureTask<Integer> futureTask = new FutureTask(thread3);
        for (int i = 0; i < 10; i++) {
            //返回值主线程的名称和执行代号
            System.out.println(Thread.currentThread().getName() + "" + i);
            if (i == 2) {
                //用Thread实例包装FutureTask实例
                new Thread(futureTask, "Callable线程").start();
                //该方法将导致主线程被阻塞，直到call()方法结束并返回为止
//                System.out.println("子线程的返回值"+futureTask.get());
            }
        }
        try {
            System.out.println("子线程的返回值" + futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
