package thread;

public class DeadLock {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void method1() {
        synchronized (lock1) {
            System.err.println(Thread.currentThread().getName() + "获得 lock1");
            synchronized (lock2) {
                System.err.println(Thread.currentThread().getName() + "获得 lock2");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void method2() {

        synchronized (lock2) {
            System.err.println(Thread.currentThread().getName() + "获得 lock2");
            synchronized (lock1) {
                System.err.println(Thread.currentThread().getName() + "获得 lock1");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("main thread1");
        new Thread(new Runnable(){
            @Override
            public void run() {
                method2();
            }
        }, "thread2").start();
        DeadLock.method1();
    }
}
