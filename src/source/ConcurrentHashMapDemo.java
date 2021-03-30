package source;

import javax.swing.tree.TreeNode;
import javax.xml.soap.Node;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapDemo<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {

    /**
     * 最大容量
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 默认初始容量
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * 单个数组最大容量
     */
    static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 默认并发等级---分成多少个单独上锁的区域
     */
    private static final int DEFAULT_CONCURRENCT_LEVEL = 16;

    /**
     * 扩容因子
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     *
     */
    transient volatile Node<K, V>[] table;

    /*-------------------------构造方法--------------------------*/
    public ConcurrentHashMapDemo() {

    }

    public ConcurrentHashMapDemo(int initialCapacity) throws IllegalAccessException {
        if (initialCapacity < 0)
            throw new IllegalAccessException();
        int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ?
                MAXIMUM_CAPACITY :
                tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
        this.sizeCtl = cap;
    }

    public ConcurrentHashMapDemo(Map<? extends K, ? extends V> m) {
        this.sizeCtl = DEFAULT_CAPACITY;
        putAll(m);
    }

    public ConcurrentHashMapDemo(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, 1);
    }

    public ConcurrentHashMapDemo(int initialCapacity, float loadFactor, int concurrencyLevel) throws IllegalAccessException {
        if (!(loadFactor > 0.0f) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalAccessException();
        //concurrencyLevel 线程并发度
        if (initialCapacity < concurrencyLevel)
            initialCapacity = concurrencyLevel;
        long size = (long) (1.0 + ( long)int/loadFactor);
        int cap = (size >= (long) MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY：tableSizeFor((int) size);
        this.sizeCtl = cap;
    }


    /**
     * ConcurrentHashMap 的核心就在于其put元素时 利用synchronized局部锁 和
     * CAS乐观锁机制 大大提升了本集合的并发nengl，比JDK7分段锁性能更强
     */
    public V put(K key, V value) {
        return putVal(key, value, false);
    }


    /**
     * 当前指定数组位置无元素时，使用CAS操作 将 Node键值对 放入对应的数组下标。
     * 出现hash冲突，则用synchronized局部锁锁住，若当前hash对应的节点是链表的头节点，遍历链表，
     * 若找到对应的node节点，则修改node节点的val，否则在链表末尾添加node节点；倘若当前节点是
     * 红黑树的根节点，在树结构上遍历元素，更新或增加元素
     */
    final V putVal(K key, V value, boolean onlyIfAbsent) {
        //首先判断key和value是否为空，如果为空的话直接抛出空指针异常。
        // 注意：ConcurrentHashMap的键和值不能为空。
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        //统计节点个数
        int binCount = 0;
        for (Node<K, V>[] tab = table; ; ) {
            Node<K, V> f;
            int n, i, fh;
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                //注意！！这是一个CAS方法，将新节点放入指定位置，不用加锁阻塞线程也能保证并发安全
                if (casTabAt(tab, i, null, new Node<K, V>(hash, key, value, null)))
                    break;
            }
            //当前Map在扩容，先协助扩容，再更新值
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            else { //hash冲突
                V oldVal = null;
                //局部锁，有效减少锁竞争的发生
                synchronized (f) { //f 是 链表头节点/红黑树根节点
                    if (tabAt(tab, i) == f) {
                        //头节点
                        if (fh >= 0) {
                            binCount = 1;
                            //插入
                            //声明一个Node<K,V>类型的数组tab,循环内判断tab数组是否存在
                            for (Node<K, V> e = f; ; ++binCount) {
                                K ek;
                                //若节点已存在，修改该节点的值
                                //k相同，替换
                                if (e.hash == hash && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K, V> pred = e;
                                //在链表插新的节点
                                //节点不存在，添加到链表末尾
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K, V>(hash, key, value, null);
                                    break;
                                }
                            }
                        }
                        //如果该节点是 红黑树节点，走此逻辑
                        else if (f instanceof TreeBin) {
                            //红黑树插入节点
                            Node<K, V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K, V>) f).putTreeVal(hash, key, value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                //链表节点查过 8 ，链表转为红黑树
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                    ;
                }
            }
        }
        //统计节点个数，检查是否需要resize
        addCount(1L, binCount);
        return null;
    }


    /**
     * 树化操作
     * 在进行树化操作时会先判断table数组的长度是否小于MIN_TREEIFY_CAPACITY)，
     * 如果小于的话会使用tryPresize方法将容量扩大2倍。
     */
    private final void treeifyBin(Node<K, V>[] tab, int index) {
        Node<K, V> b;
        int n, sc;
        if (tab != null) {
            if ((n = tab.length) < MIN_TREEIFY_CAPACITY)
                tryPresize(n << 1);
            else if ((b = tabAt(tab, index)) != null && b.hash >= 0) {
                synchronized (b) {
                    if (tabAt(tab, index) == b) {
                        TreeNode<K, V> hd = null, tl = null;
                        for (Node<K, V> e = b; e != null; e = e.next) {
                            TreeNode<K,V> p=
                                    new TreeNode<K,V>(e.hash,e.key,e.val,
                                            null,null);
                            if ((p.prev=tl)==null)
                                hd=p;
                            else
                                tl.next=p;
                            tl=p;
                        }
                        setTabAt(tab,index,new TreeBin<K,V>(hd));
                    }
                }
            }
        }
    }
}
