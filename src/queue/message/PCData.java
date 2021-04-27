package queue.message;

public class PCData {
    private final int intData ;

    public PCData(int intData) {
        this.intData=intData;
    }

    public PCData(String intData){
        this.intData=Integer.valueOf(intData);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data:"+this.intData;
    }
}
