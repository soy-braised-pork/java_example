package queue;

import java.sql.SQLOutput;
import java.util.concurrent.DelayQueue;

public class DelayDemo implements Runnable {

    private DelayQueue<DelayTask> queue = new DelayQueue<DelayTask>();

    public boolean yinye = true;

    public void up(String name, String id, int money) {
        DelayTask man = new DelayTask(name, id, 1000 * money + System.currentTimeMillis());
        System.out.println("网名" + man.getName() + "身份证" + man.getId() + "交钱" + money + "块，开始上机。。。");
        this.queue.add(man);
    }

    public void down(DelayTask man) {
        System.out.println("网名" + man.getName() + "身份证" + man.getId() + "时间到下机。。。");
    }

    @Override
    public void run() {
        while (yinye) {
            try {
                DelayTask man = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("网吧开始营业");
            DelayDemo siyu = new DelayDemo();
            Thread shangwang = new Thread(siyu);
            shangwang.start();

            siyu.up("路人甲", "123", 1);
            siyu.up("路人乙", "234", 10);
            siyu.up("路人丙", "345", 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

