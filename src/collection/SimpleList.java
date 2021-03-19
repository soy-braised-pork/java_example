package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleList {

    public static void main(String[] args) {
        //数组实现
        List arrayList=new ArrayList();
        //链表实现
        List linkedList=new LinkedList();


        long start =System.currentTimeMillis();//获取毫秒值时间戳
        for (int i=0;i<10000;i++){
            arrayList.add(i);
        }
        long end =System.currentTimeMillis();
        System.out.println("array:"+(end-start));


        long start1=System.currentTimeMillis();
        for (int a=0;a<10000;a++){
            linkedList.add(a);
        }
        long end1=System.currentTimeMillis();
        System.out.println("linkedList:"+(end1-start1));


        long start2=System.currentTimeMillis();
        for (int b=0;b<arrayList.size();b++){
            arrayList.add(b);
        }
        long end2=System.currentTimeMillis();
        System.out.println("arrayList:"+(end2-start2));


        long start3=System.currentTimeMillis();
        for (int c=0;c<arrayList.size();c++){

        }
        Iterator iterator=arrayList.iterator();
        while (iterator.hasNext()){
            iterator.next();
        }
        for (Object o:arrayList){

        }
        long end3=System.currentTimeMillis();
        System.out.println("arrayList_read:"+(end3-start3));


        long start4=System.currentTimeMillis();
        int d=linkedList.indexOf(7777);
        System.out.println(d);
        for (Object o:linkedList){

        }
        long end4=System.currentTimeMillis();
        System.out.println("linkedList_read"+(end4-start4));

    }
}
