package other;


public enum SingletonTest5 {

    //枚举元素本身就是单例
    INSTANCE;

    //添加自己需要的操作
    public SingletonTest5 singletonOperation(){
        return INSTANCE;
    }
}
