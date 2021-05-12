package other;

public class SingletonTest2 {
    //类初始化，不初始化这个对象（延时加载，真正用的时候再创建）
    private static SingletonTest2 instance;

    //构造器私有化
    private SingletonTest2(){

    }

    //方法同步，调用效率低
    public static synchronized SingletonTest2 getInstance(){
        if (instance==null){
            instance=new SingletonTest2();
        }
        return instance;
    }
}
