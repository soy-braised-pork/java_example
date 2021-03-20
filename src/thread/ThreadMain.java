package thread;

public class ThreadMain {
    public static void main(String[] args) {

        Thread1 thread1=new Thread1();
        thread1.start();
        thread1.run();


        Thread2 thread2=new Thread2();
        thread2.run();


//        //这是安排给线程的事
//        Thread2 thread2=new Thread2();
//        //这是把安排好的事发给线程去做
//        Thread thread=new Thread();
//        //启动线程去运行
//        thread.start();

        System.out.println("aaa");

        /**
         * 指令重排：不能保证顺序
         *
         * 输出结果：
         * 1111
         * 2222
         * aaa
         * 1111
         */
    }
}
