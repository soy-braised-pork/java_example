package reflect;

public class KeyBoard implements USB{
    @Override
    public void connection() {
        System.out.println("键盘正在使用...");
    }

    @Override
    public void close() {
        System.out.println("键盘已断开连接！");
    }
}
