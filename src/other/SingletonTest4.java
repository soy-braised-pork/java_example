package other;

public class SingletonTest4 {

    private static class SingletonClassInstance{
        private static final SingletonTest4 instance=new SingletonTest4();

    }

    private SingletonTest4(){
    }

    public static SingletonTest4 getInstance(){
        return SingletonClassInstance.instance;
    }

    public Object readResolve(){
        return SingletonClassInstance.instance;
    }
}
