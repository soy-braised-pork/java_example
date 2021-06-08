package oop;

public class Student extends Person {

    private int clazzLevel;
    private int clazzId;
    private boolean isClazzLeader;

//    @Override
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(String id) {
//        this.id = id;
//    }

    private Integer id=3000;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

//    public void setId(Integer id){
//        super.id=3000;
//    }

    private String clazzLeaderTitle;

//    public Student(String id,int age){
//        super(id,age);
//    }

    public boolean isClazzLeader() {
        return isClazzLeader;
    }
    public void setClazzLeader(boolean clazzLeader){
        isClazzLeader=clazzLeader;
    }
    public String getClazzLeaderTitle(){
        return clazzLeaderTitle;
    }
    public void setClazzLeaderTitle(String clazzLeaderTitle){
        this.clazzLeaderTitle=clazzLeaderTitle;
    }
    public int getClazzLevel(){
        return clazzLevel;
    }
    public void setClazzLevel(int clazzLevel){
        this.clazzLevel=clazzLevel;
    }
    public int getClazzId(){
        return clazzId;
    }
    public void setClazzId(int clazzId){
        this.clazzId=clazzId;
    }

//    @Override
//    public String toString(){
//        return this.getId()+"---->"+this.getAge();
//    }
//
//    @Override
//    public boolean equals(Object obj){
//
//    }

    private class Test12{
        public void test(){
            Student student=new Student();
//            student.clazzId;
        }
    }



}
