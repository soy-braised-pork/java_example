package designmode;

import java.util.ArrayList;

abstract class ChainOfResponsibility{
    //下一个节点
    protected ChainOfResponsibility nextNode;

    public void setNextNode(ChainOfResponsibility nextNode) {
        this.nextNode = nextNode;
    }

    public ChainOfResponsibility getNextNode() {
        return nextNode;
    }

}

class Chain extends ChainOfResponsibility{
    private ArrayList<ChainOfResponsibility> arrayList=new ArrayList<>();
    protected ChainOfResponsibility nextNode;

    public Chain(){}
}


public class ChainOfResponsibilityTest {
}
