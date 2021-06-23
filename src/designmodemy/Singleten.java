package designmodemy;

class Person {

    private static Person person=new Person();

    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    //private构造函数  不能用new实例化对象，只能使用getinstance来得到对象，getinstance方法保证了每次都使用同一个对象
//    private Person(){}

    Person(){}

    public static Person getInstance(){
        return person;
    }
}

public class Singleten {
    public static void main(String[] args) {
//        Person person = new Person();
        Person person=Person.getInstance();
        Person person3=Person.getInstance();
        person.setId(1);
        System.out.println(person.getId());
        Person person1 = new Person();
        person1.setId(2);
        System.out.println(person1.getId());
    }
}

/**
 * 1、getInstance
 * 返回一个实例化对象，此对象是static的，在内存中保留着它的引用，即内存中有一块区域存放静态方法和变量，节省内存空间
 * 可以直接使用，多次调用返回一个对象
 *
 *2、new
 * 可直接调用，多次调用不是同一个对象
 *
 * */