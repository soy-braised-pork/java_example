package queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {

    private String name;
    //身份证
    private String id;
    //截止时间
    private long endTime;
    //定义时间工具类
    private TimeUnit timeUnit = TimeUnit.SECONDS;


    public DelayTask(String name, String id, long endTime) {
        this.name = name;
        this.endTime = endTime;
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    //
//    public void setName(String name) {
//        this.name = name;
//    }
//
    public String getId() {
        return this.id;
    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    /**
     * 用来判断是否到了截止时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }


    /**
     * 相互比较排序用
     */
    @Override
    public int compareTo(Delayed o) {
        DelayTask w = (DelayTask) o;
        return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit) > 0 ? 1 : 0;
    }
}
