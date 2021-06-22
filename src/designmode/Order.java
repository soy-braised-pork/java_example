package designmode;

enum Stat{
    ON,OFF
}

class Command{
    private Stat stat;
    public Command(Stat stat){
        this.stat=stat;
    }
    public String exec(){
        if (this.stat==Stat.ON){
            return "打开了";
        }else {
            return "关上了";
        }
    }
}

class Process{
    public void drive(Command c){
        System.out.println(c.exec());
    }
}

public class Order {
    public static void main(String[] args) {
        Process p=new Process();
        p.drive(new Command(Stat.ON));
        p.drive(new Command(Stat.OFF));
    }
}
