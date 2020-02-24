package alg;

import org.junit.Test;

public class TaskSchedulerTest {

    @Test
    public void leastIntervalTest() {
        char[] tasks = new char[]{'A','A','A','A','A','B','C','D','E'};
        int n = 8;
        TaskScheduler.leastInterval(tasks, n);
    }
    @Test
    public void leastIntervalTest2() {
        char[] tasks = new char[]{'A','A','A','A','A','B','C','D','E'};
        int n = 2;
        TaskScheduler.leastInterval2(tasks, n);
    }
}
