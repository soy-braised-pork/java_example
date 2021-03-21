package oop;

/**
 * 类加载过程
 */

public class StaticTest {

    //类变量；准备阶段加载
    static StaticTest staticTest = new StaticTest();

    //static块
    static {
        System.out.println(1);
    }

    //实例代码块
    //先执行
    {
        System.out.println(2);
    }

    //实例构造方法
    StaticTest() {
        System.out.println(3);
    }

    //static方法
    public static void staticFun() {
        System.out.println(4);
    }

    //实例变量
    int a = 5;

    //静态变量
    static int b = 6;
//    static final


    public static void main(String[] args) {
        //调用就是加载类的过程
        staticFun();
    }

}
