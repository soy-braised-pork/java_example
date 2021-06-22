package designmode;

class Utils{
    private static Utils utils=new Utils();

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Utils(){}

    public static Utils getInstance(){
        return utils;
    }
}

public class Singleten {
    public static void main(String[] args) {
        Utils u=Utils.getInstance();
        u.setId(12);
        Utils u1=Utils.getInstance();
        u.setId(11);
        System.out.println(u.getId());
    }
}
