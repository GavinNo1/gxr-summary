package thread.inter;

public interface Model {
    Runnable newProducer();
    Runnable newConsumer();
}
