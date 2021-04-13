package method;

import java.util.Arrays;

public class MethodTest {

    public int test1(String... args) {
        System.out.println(Arrays.toString(args));

        return args.length;
        //return AAA;
    }


    public static void main(String[] args) {
        MethodTest methodTest = new MethodTest();

        System.out.println(methodTest.test1("a", "b", "c", "c"));

        String name = AAAA.aaa.name();
        System.out.println(name);

        String[] a={"a","a","a","a","a"};
        System.out.println(methodTest.test1(a));



    }

    //定义常量
    private enum AAAA{
        aaa,
        bbb;
    }



}
