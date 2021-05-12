package other;

public class SingletonTest1 {

    private static final SingletonTest1 instance=new SingletonTest1();

    private SingletonTest1(){}

    public static SingletonTest1 getInstance(){
        return instance;
    }
}
