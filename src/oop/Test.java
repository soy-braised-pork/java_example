package oop;

/**
 * 多态
 */


public class Test extends SimpleTest {
    public Test(String name) {
        this.name=name;
    }

    public static void main(String[] args) {
        SimpleTest s = new Test("zlx");
        System.out.println(s.name);
    }
}

class SimpleTest {
    String name;
}