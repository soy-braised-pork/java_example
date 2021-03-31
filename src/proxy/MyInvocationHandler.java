package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * java底层帮助使用者调用对象的方法，并且在方法之前或之后做自己想做的事
 *
 * 前提：有一个InvocationHandler
 */

public class MyInvocationHandler implements InvocationHandler {

    //目标对象
    private Object target;

    /**
     * 构造方法
     *
     * @param target 目标对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }


    /**
     * 这个方法就是告诉java怎么执行
     *
     * @param proxy   代理对象
     * @param method    万物皆对象，方法也是对象
     * @param args    调用方法的参数
     *
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        //在目标对象的方法执行之前简单的打印一下
        System.out.println("---------------------before----------------------");

        //执行目标对象的方法
        Object result = method.invoke(target, args);

        //在目标对象的方法执行之后简单的打印一下
        System.out.println("-----------------------after----------------------");

        return result;
    }


    /**
     * 获取目标对象的代理对象
     *
     * 生成的代理对象一定包含三大条件：
     * 1、代理对象的类
     * 2、代理对象类的类加载器
     * 3、原来的对象
     *
     * @return
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),this);
    }
}
