package other;

import java.util.HashSet;
import java.util.Set;

public class SingletonTestMain {

    public static void main(String[] args) throws InterruptedException {
        Set<SingletonTest2> set=new HashSet<>();

        for (int i=0; i<1000;i++){
            new Thread(()->set.add(SingletonTest2.getInstance())).start();
        }

        Thread.sleep(10000);

        for (SingletonTest2 singletonTest2s:set){
            System.out.println(singletonTest2s);
        }
    }
}
