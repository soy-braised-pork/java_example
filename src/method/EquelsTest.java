package method;

public class EquelsTest {
    public static void main(String[] args) {
        Integer aaa = new Integer(5);
        Integer bbb = new Integer(5);

        int a = 10;
        int b = 10;

        String str1 = new String("justice");
        String str2 = new String("justice");
        String str3;
        str3 = str1;


        //内存地址是否相同
        System.out.println(aaa == bbb);  //false
        //值是否相同
        System.out.println(aaa.equals(bbb)); //true


        //对于基本数据类型，比较两个变量的值
        System.out.println(a == b);  //true
        //报错，只能比较类类型、对象
//        System.out.println(a.equals(b));

        //比较内存地址
        System.out.println(str1 == str2);  //false
        //比较值
        System.out.println(str1.equals(str2));  //true

        //创建str3时，str3指向str1，所以str3和str1指向同一个地址，存储数据自然也相同
        System.out.println(str1 == str3);  //true
        System.out.println(str1.equals(str3));  //true

        Integer c = 3;
        int d = 3;
        System.out.println(c == d);
    }
}
