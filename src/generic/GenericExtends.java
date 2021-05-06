package generic;


import oop.example.Animal;
import oop.example.Dog;

import java.util.ArrayList;
import java.util.List;

public class GenericExtends {

    //(List<? extends Animal> animals) 不一定是animal或是其子类
    //？ 表示任何animal子类 通配符
    static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals){
            retVal+=animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1(List<Animal> animals){
        int retVal=0;
        for (Animal animal:animals){
            retVal+=animal.countLegs();
        }
        return retVal;
    }

    public static void main(String[] args) {
        List<Dog> dogs=new ArrayList<>();
        //不会报错
        countLegs(dogs);
//        //会报错
//        countLegs1(dogs);
    }

}
