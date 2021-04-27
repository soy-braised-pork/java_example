package queue;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentQueue {
    public static void main(String[] args) {

        //高性能无阻塞无界队列：ConcurrentLinkedList

        ConcurrentLinkedDeque<String> q = new ConcurrentLinkedDeque<>();
        q.offer("a");
        q.offer("b");
        q.offer("d");

        q.add("e");
        q.add("f");

        System.out.println("从头部取出元素，并从队列中删除 >> " + q.poll());
        //从头部取出元素，并从队列里删除
        System.out.println("删除后的长度 >> " + q.size());
        System.out.println("取出头部元素 >> " + q.peek());
        System.out.println("长度 >> " + q.size());
    }
}
