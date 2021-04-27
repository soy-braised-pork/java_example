package reflect;

import reflect.entity.Person;

import java.lang.reflect.Field;

public class SimpleReflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //一般的创建对象
        Person person=new Person();
        //方法private，无法访问，通过反射可以访问
        //p.id=

        //反射的创建对象
        String className ="reflect.entity.Person";
        Class<?> c=Class.forName(className); //JVM查找并加载指定的类,执行该类的静态代码段
        Object obj=c.newInstance();

        //获取私有字段
        Field nameField= c.getDeclaredField("name");
        Field ageFiled=c.getDeclaredField("age");

        //set方法赋值（暴力访问）
        //修改私有字段，将setAccessible设置成true
        nameField.setAccessible(true);
        ageFiled.setAccessible(true);

        //字段存在某一个对象中，要修改字段，就要以对象为基础，修改某一个对象相对应的字段
        nameField.set(obj,"aaa");
        ageFiled.set(obj,12);

        System.out.println("name:"+nameField.get(obj));
        System.out.println("age:"+ageFiled.get(obj));

        person=(Person)obj;
        System.out.println("字段设置的值：name="+person.getName()+",age="+person.getAge());
    }
}
