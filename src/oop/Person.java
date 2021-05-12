package oop;

public class Person {

    //属性 私有字段 成员变量 实例变量
    //存在某一个对象中，要修改字段，就要以对象为基础，修改某一个对象相对应的字段
    private Integer id;
    private int age;
    private String password;
    private String email;
    private String username;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
