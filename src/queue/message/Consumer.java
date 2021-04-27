package queue.message;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable {
    private BlockingDeque<PCData> queue;
    private boolean running = true;

    private static final int SLEEP = 1000;

    public Consumer(BlockingDeque<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start comsumer id=" + Thread.currentThread().getId());
        Random r = new Random();

        try {
            while (running) {
                PCData data = queue.take();
                if (null != data) {
                    int re = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format(Thread.currentThread().getId() + "------>" + "{0}*{1}={2}", data.getIntData(), data.getIntData(), re));
                    Thread.sleep(r.nextInt(SLEEP));
                }
                Thread.sleep(r.nextInt(SLEEP));
                running = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
