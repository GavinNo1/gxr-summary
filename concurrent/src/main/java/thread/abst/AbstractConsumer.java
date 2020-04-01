package thread.abst;

import thread.inter.Consumer;
import thread.inter.Producer;

public abstract class AbstractConsumer implements Consumer,Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
