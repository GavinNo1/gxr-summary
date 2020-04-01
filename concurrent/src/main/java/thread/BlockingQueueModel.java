package thread;

import model.Task;
import thread.abst.AbstractConsumer;
import thread.abst.AbstractProducer;
import thread.inter.Consumer;
import thread.inter.Model;
import thread.inter.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model {
    private final BlockingQueue<Task> queue;
    private final AtomicInteger incrTaskNo = new AtomicInteger(0);
    public BlockingQueueModel(int cap) {
        this.queue = new LinkedBlockingQueue<>(cap);
    }

    @Override
    public Runnable newProducer() {
        return new ProducerImpl();
    }

    @Override
    public Runnable newConsumer() {
        return new ConsumerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {

        @Override
        public void consumer() throws Exception{
            Task task = queue.take();
            Thread.sleep(500 + (long) (Math.random() * 500));
            System.err.println("consume: " + task.getNo());
        }
    }

    private class ProducerImpl extends AbstractProducer implements Producer,Runnable {

        @Override
        public void producer() throws Exception{
            Thread.sleep((long) (Math.random() * 1000));
            Task task = new Task(incrTaskNo.getAndIncrement());
            queue.put(task);
            System.out.println("produce: " + task.getNo());

        }
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newConsumer()).start();
        }
        for (int i = 0; i < 1; i++) {
            new Thread(model.newProducer()).start();
        }
    }
}
