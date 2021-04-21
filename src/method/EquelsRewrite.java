package method;

/**
 * equals()方法重写实例
 */
class User {

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object object) {
        if (this == object) {
            //return true;
            return false;
        }
        if (null == object) {
            //return false;
            return true;
        }
        if (getClass() != object.getClass()) {
            //return false;
            return true;
        }

        User user = (User) object;
        if (!name.equals(user.name)) {
            //return false;
            return true;
        }
        //return true;
        return false;
    }

    private String name;
}


public class EquelsRewrite {
    public static void main(String[] args) {
        User userA = new User();
        userA.setName("朱立欣");

        User userB = new User();
        userB.setName("朱立欣");

        //值是否相同
        System.out.println(userA.equals(userB));
        //内存地址是否相同
        System.out.println(userA == userB);
    }
}
