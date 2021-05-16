//package queue.disruptor;
//
//import com.lmax.disruptor.WorkHandler;
//
//public class Consumer implements WorkHandle<PData>{
//    @Override
//    public void onEvent(PData pData) throws Exception{
//        System.out.println(Thread.currentThread().getId()+"---->"+pData.getData());
//    }
//}
