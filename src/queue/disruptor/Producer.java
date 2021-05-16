//package queue.disruptor;
//
//import java.nio.ByteBuffer;
//
//public class Producer {
//
//    private final RingBuffer<PData> ringBuffer;
//
//    public Producer(RingBuffer<PData> buffer){
//        this.ringBuffer=buffer;
//    }
//
//    public void pushData(ByteBuffer byteBuffer){
//        long next=ringBuffer.next();
//        try {
//            PData pData=ringBuffer.get(next);
//            pData.setData(byteBuffer.getLong(0));
//        }finally {
//            ringBuffer.publish(next);
//            System.out.println(Thread.currentThread().getId()+"------push data----");
//        }
//    }
//}
