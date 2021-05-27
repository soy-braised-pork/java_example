class Test {


    protected int id;
    public String name; //签名

    public void dis() {
        System.out.println(this.id + "\t" + this.name);
    }


    public static void xxx() {

    }

};


public class Test1{
    public static void main(String[] args) {
        Test test=new Test();
        test.id=1;
        test.name="xxx";
    }
}

