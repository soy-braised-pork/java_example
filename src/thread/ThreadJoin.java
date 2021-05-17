package thread;

public class ThreadJoin {

    private volatile static int i=0;

    public static class ThreadAdd extends Thread{
        @Override
        public void run(){
            for (;i<1000000000;i++);
        }


        //主线程
        public static void main(String[] args) throws InterruptedException {
            //子线程
            ThreadAdd threadAdd=new ThreadAdd();
            //启动一个线程
            threadAdd.start();
            //谁调用了join，优先级会提高，主线程需等待子线程运行完之后。才能往下走
            threadAdd.join();
            int j=0;
            System.out.println(j);
        }
    }

}
