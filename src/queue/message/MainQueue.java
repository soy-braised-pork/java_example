package queue.message;

import java.util.concurrent.*;

public class MainQueue {

    public static void main(String[] args) {
        BlockingDeque<PCData> queue = new LinkedBlockingDeque<>(10);
        ExecutorService producerThreadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer(queue, i);
            try {
                producerThreadPool.execute(producer);
            }finally {
                countDownLatch.countDown();
            }
        }

        ExecutorService consumerThreadPool=Executors.newCachedThreadPool();
        CountDownLatch countDownLatch1=new CountDownLatch(3);
        for (int i=0;i<3;i++){
            try {
                consumerThreadPool.execute(new Consumer(queue));
            }finally {
                countDownLatch1.countDown();
            }
        }
        try {
            countDownLatch.await();
            countDownLatch1.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        producerThreadPool.shutdown();
        consumerThreadPool.shutdown();
    }
}
