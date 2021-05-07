package generic;

import generic.impl.GenericInterfaceImpl;

import java.util.ArrayList;
import java.util.List;

public class JustProve {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        Class classStringArrayList=stringList.getClass();
        Class classIntegerArrayList=integerList.getClass();

        if (classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("类型相同");
        }
        System.out.println(new GenericInterfaceImpl().next());
    }
}
