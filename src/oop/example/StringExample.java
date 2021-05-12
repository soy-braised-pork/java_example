package oop.example;

public class StringExample {

    public static void main(String[] args) {

        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = "kv" + "ill";

        System.out.println(s0 == s1);
        System.out.println(s0 == s2);
        System.out.println(s1 == s2);
    }
}
