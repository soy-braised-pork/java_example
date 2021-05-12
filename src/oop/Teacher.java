package oop;

public class Teacher extends Person{

    private String[] subject;
    private int teachAge;
    private boolean isClazzManager;

//    public Teacher(String id,int age){
//        super(id,age);
//    }

    public String[] getSubject(){
        return subject;
    }
    public void setSubject(String[] subject){
        this.subject=subject;
    }
}
