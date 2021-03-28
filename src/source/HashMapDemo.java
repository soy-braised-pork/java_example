//package source;
//
//import javax.swing.tree.TreeNode;
//import javax.xml.soap.Node;
//import java.io.Serializable;
//import java.util.*;
//
//import static java.util.Objects.hash;
//
//
//
///**
// * 底层：动态数组，数组中元素存放的是 链表或红黑树
// * 基于hash表对map的实现
// */
//
//public class HashMapDemo<K, V> extends AbstractMap<K, V> implements Map<K, V>,
//        Cloneable, Serializable {
//
//    /**
//     * 初始化容量，位运算
//     */
//    static final int DEFAULT_INITAL_CAPACITY = 1 << 4; //16,左移4位
//
//    /**
//     * 最大容量
//     */
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    /**
//     * 扩容因子，使用容量达到 当前容量的 75% 就扩容
//     */
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    /**
//     * 当前 hashmap 所能容纳键值对数量的最大值，超过这个值，则需扩容
//     */
//    int threshold;
//
//    /**
//     * 已使用的容量
//     */
//    transient int size;  //transient 不参与序列化过程
//
//    /**
//     * Node数组，实际存放 键值对 的地方
//     */
//    transient Node<K, V>[] table;
//
//    /**
//     * 链表转红黑树的阀值，链表长度达到此值，会进化成红黑树
//     */
//    static final int TREEIFY_THRESHOLD = 8;
//
//    /**
//     * 系列构造方法，推荐在初始化时根据实际情况设置好初始值，用好了可以显著减少 resize，提升效率
//     */
//    public HashMapDemo(int initialCapacity, float loadFactor) throws IllegalAccessException {
//        if (initialCapacity < 0)
//            throw new IllegalAccessException("Illegal initial capacity:" + initialCapacity);
//        if (initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor:" + loadFactor);
//        this.loadFactor = loadFactor;
//        this.threshold = tableSzieFor(initialCapacity);
//    }
//
//    public HashMapDemo(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    public HashMapDemo() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//    }
//
//    public HashMapDemo(Map<? extends K, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
//    }
//
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    @Override
//    public Set<Entry<K, V>> entrySet() {
//        return null;
//    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, i;
//        //初始化桶数组 table ，table 被延迟到插入新数据时再进行初始化
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        //如果桶中不包含键值对节点引用，则将新键值对节点的引用存入桶中即可
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//        else {
//            Node<k, V> e;
//            K k;
//            //如果键的值以及节点hash等于链表中的第一个键值对节点时，则将e指向该键值对
//            if (p.hash == hash && (k = p.hash) == key != null && key.equals(k))
//                e = p;
//                //如果桶中的引用类型为TreeNode，则调用红黑树的插入方法
//            else if (p instanceof TreeNode)
//                e = ((TreeNode<K, V>) p).putTreeval(this, tab, hash, key, value);
//            else {
//                //对链表进行遍历，并统计链表长度
//                for (int binCount = 0; ; ++binCount) {
//                    //链表中不包含要插入的键值对节点时，则将该节点接在链表最后
//                    //!!!JDK1.7中 头插法      1.8尾插法
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        //如果链表长度达到阀值，则进化成红黑树
//                        if (binCount >= TREEIFY_THRESHOLD - 1)
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//
//                    //条件为true，表示当前链表包含要插入的键值对，终止遍历
//                    if (e.hash == hash && ((k = e.key) == key || (key != null && k.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//
//            //判断要插入的键值对是否存在 HashMap 中
//            if (e != null) {
//                V oldValue = e.value;
//                // onlyIfAbsent 表示是否仅在 oldValue 为 null 的情况下更新键值对的值
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        //键值对数量超过阀值时，则进行扩容
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//    /**
//     * 扩容为原容量的两倍，并将存在的元素 放到新数组上
//     */
//    final Node<K, V>[] resize() {
//        Node<K, V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        //如果 table 不为空，表明已经初始化过了
//        if (oldCap > 0) {
//            //当 table 容量超过容量最大值，则不再扩容
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            //按旧容量和阀值的2倍计算新容量和阀值的大小
//            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITAL_CAPACITY)
//                newThr = oldThr << 1;
//        } else if (oldThr > 0)
//            //初始化时，将 threshold 的值赋值给 newCap
//            //HashMap 使用 threshold 变量暂时保存 initialCapacity 参数的值
//            newCap = oldThr;
//        else {
//            //调用无餐构造方法时，桶数组容量为默认容量
//            //阀值为默认容量*默认负载因子
//            newCap = DEFAULT_INITAL_CAPACITY;
//            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITAL_CAPACITY);
//        }
//
//        //newThr 为 0 时，按阀值计算公式进行计算
//        if (newThr == 0) {
//            float ft = (float) newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        //创建新的桶数组，桶数组的初始化也是在这里完成的
//        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            //如果旧的桶数组不为空，则遍历桶数组，并将键值对映射到新的桶数组中
//            for (int j = 0; j < oldCap; ++j) {
//                Node<K, V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof TreeNode)
//                        //重新映射时，需要对红黑树进行拆分
//                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
//                    else {
//                        Node<K, V> loHead = null, loTail = null;
//                        Node<K, V> hiHead = null, hiTail = null;
//                        Node<K, V> next;
//                        //遍历链表，并将链表节点按原顺序分组
//                        do {
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            } else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        //将分组后的链表映射到新桶中
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//    public V get(Object key) {
//        Node<K, V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    /**
//     * 根据 hash 和 key 获取相应的 Node节点
//     */
//    final Node<K, V> getNode(int hash, Object key) {
//        Node<K, V>[] tab;
//        Node<K, V> first, e;
//        int n;
//        K k;
//        //1、定位键值对所在桶的位置，如果该位置有元素，则获取第一个元素
//        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
//            //如果hash和key都与 第一个元素相同，则第一个元素就是我们要获取的，直接返回
//            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            if ((e = first.next) != null) {
//                //2、如果first 是 TreeNode 类型，则调用红黑树查找方法
//                if (first instanceof TreeNode)
//                    return ((TreeNode<K, V>) first).getTreeNode(hash, key);
//                //3、对链表进行查找
//                do {
//                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * HashMap底层的动态数组的定义 transient Node<K,V>[] table
//     * 这里很明显是一个单向链表结构
//     */
//    static class Node<K,V> implements Map.Entry<K,V> {
//        final int hash;
//        final K key;
//        V value;
//        Node<K,V> next;
//
//        Node(int hash, K key, V value, Node<K,V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        public final K getKey()        { return key; }
//        public final V getValue()      { return value; }
//        public final String toString() { return key + "=" + value; }
//
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//
//    /**
//     * JDK8 加入的 红黑树TreeNode内部类，红黑树的方法比较复杂，这里只展示一些重要的属性结构代码
//     */
//
//    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V>{
//        TreeNode<K,V> parent;
//        TreeNode<K,V> left;
//        TreeNode<K,V> right;
//        TreeNode<K,V> prev;
//        //颜色，true红  false黑
//        boolean red;
//        TreeNode(int hash,K key,V Val,Node<K,V> next){
//            super(hash,key,val,next);
//        }
//    }
//
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
