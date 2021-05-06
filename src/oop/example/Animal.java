package oop.example;

public class Animal {
    public String hand="1";
    private int foot=2;

    public Animal(){

    }

    public void eat(){
        System.out.println("eat~");
    }

    public void sleep(){
        System.out.println("sleep!");
    }

    public String sleep1(){
        return "sleep1!";
    }

    public int countLegs(){
        return foot;
    }


}
