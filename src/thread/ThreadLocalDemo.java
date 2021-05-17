package thread;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal
 * 线程本地局部变量
 * 只有一个线程才能操作--->线程安全
 * web领域
 */

public class ThreadLocalDemo {

//    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<>();

    public static class ParseData implements Runnable {

        int i = 0;

        public ParseData(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (t1.get() == null) {
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date time = t1.get().parse("2020-5-17 19:20:" + i % 60);
//            Date time = new sdf.parse("2021-5-17 19:21:00" + i % 60);
                System.out.println(i + "--->" + time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1100; i++) {
            threadPool.submit(new ParseData(i));
        }
    }
}
