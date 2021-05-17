import java.util.ArrayList;
import java.util.List;

public class TestVM {

    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
//        List<TestVM> list=new ArrayList<>();
//        while (true){
//            list.add(new TestVM());
//            Thread.sleep(30);
//        }
        TestVM testVM = new TestVM();
        synchronized (testVM) {
            testVM.test1();
            testVM.test2(1);
        }
    }

    public void test1() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a");
        stringBuffer.append("b");
        stringBuffer.append("c");
//        TestVM testVM=new TestVM();
//
//        testVM.test2(count);
//        return count;
    }

    public void test2(Integer i){

    }
}
