package queue;

import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {
    public static void main(String[] args) {

        PriorityBlockingQueue<Task> q2 = new PriorityBlockingQueue<>();

        Task t1 = new Task();
        t1.setId(3);
        t1.setName("id为3");

        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id为4");

        Task t3=new Task();
        t3.setId(1);
        t3.setName("id为1");

        Task t4=new Task();
        t4.setId(2);
        t4.setName("id为2");

        q2.add(t1);
        q2.add(t2);
        q2.add(t3);
        q2.add(t4);

        //
    }
}
