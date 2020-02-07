package demo;

public class DeadLock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    //先拿第一个锁，再拿第二个锁
    private static void fisrtToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (lock1){
            System.out.println(threadName+" get first lock");
            Thread.sleep(100);
            synchronized (lock2){
                System.out.println(threadName+" get second lock");
            }
        }
    }

    //先拿第二个锁，再拿第一个锁
    private static void SecondToFisrt() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (lock2){
            System.out.println(threadName+" get second lock");
            Thread.sleep(100);
            synchronized (lock1){
                System.out.println(threadName+" get first lock");
            }
        }
    }

    private static class TestThread extends Thread{

        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        public void run(){
            Thread.currentThread().setName(name);
            try {
                SecondToFisrt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("TestDeadLock");
        TestThread testThread = new TestThread("SubTestThread");
        testThread.start();
        try {
            //主线程，先拿第1个锁，再拿第2个锁。
            fisrtToSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
