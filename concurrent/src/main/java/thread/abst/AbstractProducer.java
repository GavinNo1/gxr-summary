package thread.abst;

import thread.inter.Producer;

public abstract class AbstractProducer implements Producer,Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                producer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
