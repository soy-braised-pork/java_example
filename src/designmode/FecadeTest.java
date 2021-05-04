package designmode;


import java.util.HashMap;

class Util {
    public static HashMap hashMap = new HashMap();

    static {
        hashMap.put("xxx.html", "#User.name,#User.sex");
    }
}

class User {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

class Fecade {

    public void render() {

    }

    public void write() {

    }
}

public class FecadeTest {
    public static void main(String[] args) {
        Fecade fecade = new Fecade();
        fecade.render();
        fecade.write();
        System.out.println();
        System.out.println();
    }
}
