package alg;

import org.junit.Test;

public class TrapTest {
    @Test
    public void trap1Test() {
        int height[] = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        Trap.trap1(height);
    }
    @Test
    public void trap2Test() {
        int height[] = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        Trap.trap2(height);
    }
    @Test
    public void trap3Test() {
        int height[] = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        Trap.trap3_2(height);
    }
}
