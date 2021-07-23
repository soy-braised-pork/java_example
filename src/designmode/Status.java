package designmode;

import java.util.HashMap;

enum StatEnum{
    START,OK,ERR
}


class Statustest{
    public Process_Node getCurrentStat() {
        return currentStat;
    }
    public void setCurrentStat(Process_Node currentStat) {
        this.currentStat = currentStat;
    }
    private Process_Node currentStat;

    @Override
    public String toString() {
        return this.currentStat.currentStat.toString();
    }
}

class Process_Node{
    public Flow currentStat;
    public Process_Node ok;
    public Process_Node err;
    public Process_Node(Flow status){
        this.currentStat=status;
    }
}
enum Flow{
    START,A,B,STOP,OK
}
class Process{
    public Status getCurrentStatus(String name){
        Status s=process.get(name);
        return s;
    }
    public static Process_Node head=new Process_Node(Flow.START);
    static{
        Process_Node a=new Process_Node(Flow.A);
        head.ok=a;
        head.err=null;
        a.ok=new Process_Node(Flow.B);
        a.err=null;
    }
    private HashMap<String,Status> process=new HashMap<>();
    public void commit(String name){
        Status s=new Status();
        s.setCurrentStat(Process.head);
        process.put(name,s);
    }
    public void next_OK(String name){
        Status s=process.get(name);
        Process_Node tmp=s.getCurrentStat();
        tmp=tmp.ok;
        Status s2=new Status();
        s2.setCurrentStat(tmp);
        process.put(name,s2);
    }
    public void next_err(String name){
        Status s=process.get(name);
        Process_Node tmp=s.getCurrentStat();
        tmp=tmp.err;
        Status s2=new Status();
        s2.setCurrentStat(tmp);
        process.put(name,s2);
    }
}


public class Status {
    public static void main(String[] args) {
        Process p=new Process();
        p.commit("xxx");
        p.commit("yyy");
        Status s=p.getCurrentStatus("yyy");
        System.out.println(s);
        p.next_OK("xxx");
        p.next_OK("yyy");
        Status s2=p.getCurrentStatus("yyy");
        System.out.println(s2);
        p.next_OK("xxx");
        p.next_OK("yyy");
        Status s3=p.getCurrentStatus("yyy");
        System.out.println(s3);
    }
}
