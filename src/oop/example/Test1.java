package oop.example;

public class Test1 {

    public static void main(String[] args) {
        Animal a1 = new Animal();
        Animal a2 = new Dog();
        Dog a3 = new Dog();

        a1.eat();

        a1.sleep();

//        String s = a1.sleep1();

        System.out.println(a2.hand);
        a2.eat();
        int a = 9;
        int b = 1;
        int c = a + b;

        System.out.println(c);

        System.out.println("bbb");
        String[] array = {"a", "b", "c", "d"};

//        for (int i=0;i<array.length;i++){}

        for (String s1 : array) {
            System.out.println(s1);
        }

        String str = "asdfghjk";
        String str1 = new String("qwertyuiop");

        String concat = str.concat(str1);

        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();

        int[] strArray = {1, 2, 3, 4};

        String[] strArray1=new String[]{};
    }
}
