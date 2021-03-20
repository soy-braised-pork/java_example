package thread;

/**
 * 多线程程序编写步骤：
 * 1、明确线程要干的事（写在run方法中）
 * 2、启动相关线程，做指定的事
 */

//继承Thread方法，重写run方法
public class Thread1 extends Thread {

    @Override
    public void run() {
        System.out.println("1111");
    }
}
