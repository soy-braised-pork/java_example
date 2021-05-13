package queue.disruptor;

public class PDataFactory implements EventFactory<PData>{

    @Override
    public PData newTnstance(){
        return new PData();
    }
}
