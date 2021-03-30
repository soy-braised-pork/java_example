package oop;

import annotation.Person;
import sun.misc.Launcher;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

/**
 * 类加载过程
 */

public class StaticTest {

    //类变量；准备阶段加载
    //要想创建这个类变量，就得先把这个类加载完
    static StaticTest staticTest = new StaticTest();

    //static块
    static {
        System.out.println("static块");
    }

    //实例代码块
    //先执行
    {
        System.out.println("实例代码块");
    }

    //实例构造方法
    StaticTest() {
        System.out.println("实例构造方法";
    }

    //static方法
    public static void staticFun() {
        System.out.println("static方法");
    }

    //实例变量
    int a = 5;

    //静态变量 类变量
    static int b = 6; //在准备阶段，这里的b值是0，b同时也在准备阶段赋值成6  准备：赋值+分配内存
//    static final   常量在编译时就已赋值


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        //调用就是加载类的过程
        staticFun();


        //核心类库
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

        // ClassLoader 指定加载相应的类
        // ClassLoader.getSystemClassLoader().getResource();

        /**
         * 创建对象的方法：
         */

        //1、new
        StaticTest staticTest = new StaticTest();


        //2、根据某一个类的全名拿到类的对象 ----->  反射
        Class<?> aClass = Class.forName("oop.StaticTest");
        StaticTest staticTest1 = (StaticTest) aClass.newInstance();

//        for (Constructor<?> constructor : constructors) {
//            System.out.println(constructors);
//        }
        Constructor<Person> constructor = Person.class.getConstructor(Integer.class, Integer.class, String.class);
        Person person = constructor.newInstance(1, 123, "aaaa");

//
//        //3、通过clone的方式
//        Constructor<Person> constructor1 = Person.class.getConstructor(Integer.class, Integer.class, String.class);
//        Person person1 = constructor.newInstance(2, 123, "aaa");
//        Person clone = (Person) person1.clone();
//
//        //序列化与反序列化
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test.abc"));
//        ObjectOutputStream.writeObject(clone);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test.abc"));
        Person object = (Person) objectInputStream.readObject();

        String aaa = "";

        String aaa1 = new String("");

    }

}
