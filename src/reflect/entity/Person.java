package reflect.entity;

public class Person {
    private String id;
    private String name;
    private int age;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void fun1(){
        System.out.println("function1");
    }
    public void fun2(){
        System.out.println("function2");
    }

    public void fun3(String p){
        System.out.println(p+"--->fun3");
    }

    private void fun4(){
        System.out.println("function4");
    }
}
