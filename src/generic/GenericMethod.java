package generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericMethod {

//    public static void fromArrayToCollection(Object[] arrays, Collection<Object> collection){
//        for (Object o:arrays){
//            collection.add(o);
//        }
//    }

    private static <T> void fromArrayToCollection(T[] arrays, Collection<T> collection) {
        for (T o : arrays) {
            collection.add(o);
        }
    }

    public static void main(String[] args) {
        String[] strArrays = {"a", "b"};
        List<Object> strList=new ArrayList<>();
        List<String> stringList=new ArrayList<>();
        fromArrayToCollection(strArrays,stringList);
    }
}
