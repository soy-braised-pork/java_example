package thread;

public class AccountingTest implements Runnable{

    static AccountingTest test=new AccountingTest();

    static volatile int i=0;

    public synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        for (int j=0;j<100000000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(test);
        Thread t2=new Thread(test);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
