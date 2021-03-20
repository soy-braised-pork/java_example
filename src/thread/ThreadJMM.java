package thread;

public class ThreadJMM {

    //volatile
    //让多个线程所共享的变量在修改后能够在第一时间知道被修改的新值
    //立即刷新缓存
    private volatile static boolean initData = false;


    private static void refresh() {
        System.out.println("change data");
        initData = true;
    }


    private static void loadData() {
        while (!initData) {
            System.out.println("1");
        }
        System.out.println("数据initData被改变-------");
    }

    //主线程
    public static void main(String[] args) {
        //t1
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        },"t1").start();
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //t2
        new Thread(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        },"t2").start();
    }
}
