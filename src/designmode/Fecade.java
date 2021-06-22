package designmode;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;

class Util{
    public static HashMap page =new HashMap();
    static {
        page.put("c:/xxx.html","id=#User.id,username=#User.name,age=#User.age,sex=#User.sex");
    }
}

class User{
    private String sex;
    private int id;
    private int age;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


interface IDoProcess{
    public String render(String path,Object u) throws Exception;
    public String writehtml(String path);
}


class DoProcess implements IDoProcess{
    @Override
    public String render(String path, Object u) throws Exception {
        String str= (String) Util.page.get(path);
        User user= (User) u;
        Class clz=user.getClass();
        Field[] fs=clz.getDeclaredFields();
        for (Field f:fs){
            String mname=f.getName();
            String tmp="get"+mname.substring(0,1).toUpperCase()+mname.substring(1,mname.length());
//            System.out.println(tmp);
            Method m=clz.getDeclaredMethod(tmp);
            String tmpstr="#"+clz.getSimpleName()+"."+f.getName();
//            System.out.println(tmpstr);
            str=str.replaceAll(tmpstr,m.invoke(user)+"");
        }
        return str;
    }

    @Override
    public String writehtml(String path) {
        String str= (String) Util.page.get(path);
        return str;
    }
}

class FecadeTest{
    private IDoProcess doProcess=new DoProcess();
    public void render(String path,Object u) throws Exception {
        String str=doProcess.render(path, u);
        System.out.println(str);
    }
    public void writehtml(String path){
        String str=doProcess.writehtml(path);
        System.out.println(str);
    }
}

public class Fecade {
    public static void main(String[] args) throws Exception {
        FecadeTest f=new FecadeTest();
        User u = new User();
        u.setAge(22);
        u.setId(1);
        u.setSex("女");
        f.render("c:/xxx.html",u);
        f.writehtml("c:/xxx.html");
        
    }
}
