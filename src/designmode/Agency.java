package designmode;


import java.util.LinkedList;

class Product {
    public MyBlockQueue myBlockQueue;

    public Product(MyBlockQueue myBlockQueue) {
        this.myBlockQueue = myBlockQueue;
    }

    public boolean pub(String str) {
        if (myBlockQueue.getSize()>= myBlockQueue.getLimit()){
            return false;
        }else {
            myBlockQueue.push(str);
            return true;
        }
    }
}


class Consumer {
    public MyBlockQueue myBlockQueue;
    public Consumer(MyBlockQueue myBlockQueue){
        this.myBlockQueue=myBlockQueue;
    }
    public String sub(){
        if (myBlockQueue.getSize()<=0){
            return null;
        }else {
            return myBlockQueue.pop();
        }
    }
}


class MyBlockQueue {
    private LinkedList<String> arr = new LinkedList<>();

    private int limit = 0;

    public int getLimit() {
        return limit;
    }

    public MyBlockQueue(int limit) {
        this.limit = limit;
    }

    public void push(String str) {
        arr.addFirst(str);
    }

    public String pop() {
        return arr.removeLast();
    }

    public int getSize() {
        return arr.size();
    }
}


public class Agency {
    public static void main(String[] args) {
        MyBlockQueue myBlockQueue = new MyBlockQueue(3);
        Product p=new Product(myBlockQueue);
        Consumer c=new Consumer(myBlockQueue);
        System.out.println(p.pub("1"));
        System.out.println(p.pub("2"));
        System.out.println(p.pub("3"));
        System.out.println(p.pub("4"));
        System.out.println(c.sub());
        System.out.println(c.sub());
        System.out.println(c.sub());
        System.out.println(p.pub("6"));
    }
}
