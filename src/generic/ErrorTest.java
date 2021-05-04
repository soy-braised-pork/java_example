package generic;

import java.util.ArrayList;
import java.util.List;

public class ErrorTest {

    public static void main(String[] args) {
        List arrayList=new ArrayList();
        arrayList.add("aaa");
        arrayList.add(100);

        for (int i=0;i<arrayList.size();i++){
            String item= (String) arrayList.get(i);
            System.out.println("item="+item);
        }
    }
}
