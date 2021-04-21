package collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleList1 {
    public static void main(String[] args) {
        //数组实现
        List arrayList = new ArrayList();
        arrayList.add(1);
        //多存一个hash
        arrayList.add("aa");

        arrayList.add(34);
        System.out.println(arrayList);

        //链表实现
        List list = new LinkedList();
        list.add(1);
        //多存一个hash
        list.add("aa");
        list.add(34);
        list.add(3,123);
        list.add(4,"a");
        System.out.println(list);
    }
}
