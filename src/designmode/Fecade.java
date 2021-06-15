package designmode;

import java.lang.reflect.Constructor;
import java.util.HashMap;

interface IToy{
    void doplay();
}


class ToyGun_1 implements IToy{
    private String name;
    public ToyGun_1(String name){
        this.name=name;
    }

    @Override
    public void doplay() {
        System.out.println("手枪 瞄准 开枪："+this.name);
    }
}

class ToyGun_2 implements IToy{
    private String name;
    public ToyGun_2(String name){
        this.name=name;
    }
    @Override
    public void doplay() {
        System.out.println("机枪 瞄准 开枪："+this.name);
    }
}

class ToyGunFactory{
    public static HashMap<String,Class> types=new HashMap();
    static {
        types.put("gun1",ToyGun_1.class);
        types.put("gun2",ToyGun_2.class);
    }
    public static Object create(String type,String name) throws Exception {
        Class clz= (Class) types.get(type);
        Constructor con=clz.getConstructor(String.class);
        return con.newInstance(name);
    }
}

public class Fecade {
    public static void main(String[] args) throws Exception {
        IToy toy= (IToy) ToyGunFactory.create("gun2","xxx");
        toy.doplay();
    }

}
