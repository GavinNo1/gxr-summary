package thread;

import java.util.concurrent.CountDownLatch;

public class WaitSubThread {
    public final static CountDownLatch countDownLatch = new CountDownLatch(5);
    public static void main(String[] args) {
        Thread.currentThread().setName("main thread");
        try {
            for (int i = 0; i < 5; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        countDownLatch.countDown();
                        System.err.println(Thread.currentThread().getName()+ "减1");
                    }
                }, "thread"+ i).start();
            }

            countDownLatch.await();
            System.err.println(Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
