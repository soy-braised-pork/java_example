package generic;

//泛型伪概念  虚拟机里没有泛型类型对象----所有对象都属于普通类
public class GenericClazz<T> {

    //key这个成员变量的类型为T，T的类型由外部指定
    private T key;

    public GenericClazz(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer
        GenericClazz<Integer> genericInteger = new GenericClazz<>(12300);

        //传入的实参类型需与泛型的类型参数类型相同
        GenericClazz<String> genericString = new GenericClazz<>("fdf");

        System.out.println("泛型测试key is" + genericInteger.getKey());
        System.out.println("泛型测试key is" + genericString.getKey());
    }
}
