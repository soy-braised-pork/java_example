package queue.disruptor;

import queue.message.Producer;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PDataFactory dataFactory = new PDataDactory;
        int bufferSize = 1024;
        Disruptor<PData> disruptor = new Disruptor<>(
                dataFactory,
                bufferSize,
                executorService,
                ProducerType.MULIT,
                new BlockingWaitStrategy()
        );
        disruptor.handleEventsWitWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );

        disruptor.start();

        Producer producer = new Producer(disruptor.getRingBuffer());
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        for (long i = 0; true; i++) {
            byteBuffer.putLong(0, i);
            producer.pushData(byteBuffer);
            Thread.sleep(1000);
            System.out.println("data>>>>>>>>>>>>>>" + i);
        }
    }
}
