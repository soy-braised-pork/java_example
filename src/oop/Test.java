package oop;

/**
 * 多态
 */


public class Test extends SimpleTest {
    public static void main(String[] args) {
        SimpleTest s = new Test();
        System.out.println(s);
    }
}

class SimpleTest {
    private final String name = "zlx";

}