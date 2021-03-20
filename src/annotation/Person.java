package annotation;

public class Person {

    /**
     * empty()
     * 1、@Deprecated 表示empty()已过期，不建议使用
     * 2、@TestAnnotation  表示empty()方法对应的MyAnnotation的value值是默认值“unknown”
     */
    @TestAnnotation
    @Deprecated
    public void empty(){
        System.out.println("\nempty");
    }

    /**
     * somebody() 被@MyAnnotation(value = {"girl","boy"})所标注
     * @TestAnnotation(value = {"girl","boy"}),表示MyAnnotation的value值是{"girl","boy"}
     */
    @TestAnnotation(value = {"girl","boy"})
    public void somebody(String name,int age){
        System.out.println("\nsomebody:"+name+","+age);
    }

}
