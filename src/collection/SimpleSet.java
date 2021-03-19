package collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SimpleSet {
    public static void main(String[] args) {
        Set hashSet =new HashSet();
        Set treeSet=new TreeSet();

        //hashset 无序去重
        hashSet.add("t");
        hashSet.add("t");
        hashSet.add("d");
        hashSet.add("v");
        hashSet.add("e");
        boolean i=hashSet.add("e");
        System.out.println(hashSet);


        //treeset 按照ASC码有序排列  去重
        treeSet.add("t");
        treeSet.add("t");
        treeSet.add("d");
        treeSet.add("v");
        treeSet.add("e");
        boolean f=treeSet.add("e");
        System.out.println(treeSet);
    }
}
