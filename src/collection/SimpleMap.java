package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 位置=散列码%桶总数
 *
 * 为何桶数设置为素数可防止键的聚集？为何是2的幂
 */

public class SimpleMap {
    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("a","b");
        map.put("b","c");
        map.put("d","b");
        System.out.println(map);
    }
}
