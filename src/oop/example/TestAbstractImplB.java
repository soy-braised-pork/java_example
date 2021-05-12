package oop.example;

public class TestAbstractImplB extends TestAbstract {
    public String testa() {
        return "ccc";
    }

    public static void main(String[] args) {
        TestAbstractImplB testAbstractImplB = new TestAbstractImplB();
        TestAbstractImpl testAbstractImplA = new TestAbstractImpl();

        System.out.println(testAbstractImplB.testa());
        System.out.println(testAbstractImplA.testb());
    }
}
