package queue.message;

import java.util.Random;
import java.util.concurrent.*;

public class Producer implements Runnable{
    private BlockingDeque<PCData> queue;
    private int data;
    private static final int SLEEP = 1000;

    public Producer(BlockingDeque<PCData> queue,int data){
        this.queue=queue;
        this.data=data;
    }


    @Override
    public void run() {
        PCData pcData;

        Random r=new Random();

        System.out.println("start producer id="+Thread.currentThread().getId());

        try {
            Thread.sleep(r.nextInt(SLEEP));
            pcData=new PCData(data);
            System.out.println("put data into queue"+pcData);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
