package async;

import sun.plugin2.jvm.RemoteJVMLauncher;

public class CallBackTest {
    public static void main(String[] args) {
        int a=10452;
        int b=1234;
        //实例化计算器Calculator类
        Calculator calculator=new Calculator(a,b);
        //调用计算器calculator类
        calculator.calculation();
        //控制台输出
        System.out.println("/======================/");

    }
}


//回调接口
interface CallBackInterface{
    //计算的结果回调函数
    void calculationResult(int a,int b,int result);

    public void calculation();
}

//计算的具体逻辑类
class Logic{
    //计算的具体逻辑（计算方法）
    public void calculationLogic(int a, int b, CallBackInterface callBackInterface){
        int result=a+b;
        //利用传进来的对象，回调计算结果
        callBackInterface.calculation();
    }
}

//计算器类，实现了回调接口，用于本类的实例化对象传值
class Calculator implements CallBackInterface{
    private int a,b;

    public Calculator(int a,int b){
        this.a=a;
        this.b=b;
    }

    public void calculation(){
        /*
        匿名实例化计算的具体逻辑类Logic，并调用计算函数
        this是本类对象，因为实现了回调接口CallBackInterface，所以可以传值
         */
        new Logic().calculationLogic(a,b,this);
    }

    //因为实现了回调接口CallBackInterface,必须要重写计算的结果回调函数calculationResult
    public void calculationResult(int a,int b,int result){
        //控制台输出
        System.out.println(a+"+"+b+"="+result);
    }



}