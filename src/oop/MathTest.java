package oop;

public class MathTest {

    public static MathTest Test1 = new MathTest();

    public int compute() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = a + b;
        return d;
    }


    public static void main(String[] args) {
        MathTest mathTest=new MathTest();
        mathTest.compute();

        System.out.println("abc");
        System.out.println(mathTest.compute());
    }
}
