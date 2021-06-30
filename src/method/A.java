package method;

public class A {
    public static void doSth(int x, int y) {
//        x ^= y;
//        y ^= x;
//        x ^= y;
        x=y;
        y=x;
    }

    public static void main(String[] args) {
        int x = 5;
        int y = 6;
        doSth(x, y);
        System.out.println("x=" + x + ",y=" + y);
    }
}
