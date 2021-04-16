package thread;

/**
 *锁：
 * 1、原子性
 * 2、可见性
 * 3、指令重排
 */

public class Thread3 extends Thread{


    @Override
    public void run() {
        int b=3;
        for (int a=0;a>50;a++){
            if (a%2==0){
                b++;
            }else {
                b--;
            }
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        Thread3 thread3=new Thread3();
        thread3.start();
    }

}
