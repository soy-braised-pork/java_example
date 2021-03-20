package async;

public class CallBackTest1 {
    public static void main(String[] args) {
        int a = 10976;
        int b = 9876;
        //实例化计算器Calculator类
        Calculator1 calculator1 = new Calculator1(a, b);
        //调用计算器calculator的计算函数
        calculator1.calculation();
        //控制台输出
        System.out.println("================");
    }
}

//回调接口
interface CallBackInterface1 {
    //计算的结果回调函数
    public void calculationResult(int a, int b, int result);
}

//计算的具体逻辑类
class Logic1 {
    //计算的具体逻辑（计算方法）
    public void calculationLogic(int a, int b, CallBackInterface1 callBackInterface1) throws InterruptedException {
        int result = a + b;
        //让线程等待5s
        Thread.sleep(5 * 1000);
        //利用传入的对象，回调计算结果
        callBackInterface1.calculationResult(a, b, result);
    }
}

//计算器类，实现了回调接口，用于本类的实例化对象传值
class Calculator1 implements CallBackInterface1 {
    private int a, b;

    public Calculator1(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void calculation() {
        //开启另一个子线程
        new Thread(new Runnable() {
            //覆盖
            @Override
            public void run() {
                /*匿名实例化计算的具体逻辑类Logic，并调用计算函数
                 * this是本类对象，因为实现了回调接口CallBackInterface，所以可以传值*/
                try {
                    new Logic1().calculationLogic(a, b, Calculator1.this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //因为实现了回调接口CallBackInterface，必须要重写计算的结果回调函数calculationResult
    public void calculationResult(int a, int b, int result) {
        //控制台输出
        System.out.println(a + "+" + b + "=" + result);
    }
}