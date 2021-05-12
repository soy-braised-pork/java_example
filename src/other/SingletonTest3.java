package other;

public class SingletonTest3 {

    private volatile static SingletonTest3 SingletonDemo3;

    private SingletonTest3(){
    }
    public static SingletonTest3 newInstance(){
        if (SingletonDemo3==null){
            synchronized (SingletonTest3.class){
                if (SingletonDemo3==null){
                    SingletonDemo3=new SingletonTest3();
                }
            }
        }
        return SingletonDemo3;
    }
}
