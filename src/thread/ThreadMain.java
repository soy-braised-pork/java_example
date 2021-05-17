package thread;

public class ThreadMain {
    public static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        //启动线程，调用start方法，开启一个新的线程去做事
        thread1.start();
        //run方法里写的是线程做的事，是用来当前线程自己调用的，不需要人为调用
        thread1.run();

//
//        Thread2 thread2 = new Thread2();
//        thread2.run();




        //这是安排给线程的事
        Thread2 thread2=new Thread2();
        //这是把安排好的事发给线程去做
        Thread thread=new Thread();
        //启动线程去运行
        thread.start();

        System.out.println("aaa");

        /**
         * 指令重排：不能保证顺序
         *
         * 输出结果：
         * 1111
         * runnable test------>Thread-1
         * aaa
         * 1111
         */
    }
}
