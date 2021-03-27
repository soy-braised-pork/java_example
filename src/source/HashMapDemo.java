package source;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;

/**
 * 底层：动态数组，数组中元素存放的是 链表或红黑树
 * 基于hash表对map的实现
 */

public class HashMapDemo<K, V> extends AbstractMap<K, V> implements Map<K, V>,
        Cloneable, Serializable {

    /**
     * 初始化容量，位运算
     */
    static final int DEFAULT_INITAL_CAPACITY = 1 << 4; //16,左移4位

    /**
     * 最大容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 扩容因子，使用容量达到 当前容量的 75% 就扩容
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 当前 hashmap 所能容纳键值对数量的最大值，超过这个值，则需扩容
     */
    int threshold;

    /**
     * 已使用的容量
     */
    transient int size;  //transient 不参与序列化过程

    /**
     * Node数组，实际存放 键值对 的地方
     */
    transient Node<K, V>[] table;

    /**
     * 链表转红黑树的阀值，链表长度达到此值，会进化成红黑树
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 系列构造方法，推荐在初始化时根据实际情况设置好初始值，用好了可以显著减少 resize，提升效率
     */
    public HashMapDemo(int initialCapacity, float loadFactor) throws IllegalAccessException {
        if (initialCapacity < 0)
            throw new IllegalAccessException("Illegal initial capacity:" + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity=MAXIMUM_CAPACITY;
        if (loadFactor<=0||Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor:"+loadFactor);
        this.loadFactor=loadFactor;
        this.threshold=tableSzieFor(initialCapacity);
    }

    public HashMapDemo(){

    }
}















