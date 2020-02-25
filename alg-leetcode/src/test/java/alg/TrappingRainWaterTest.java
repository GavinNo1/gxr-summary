package alg;

import org.junit.Test;

public class TrappingRainWaterTest {
    @Test
    public void trap1Test() {
        int height[] = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater.trap1(height);
    }
    @Test
    public void trap2Test() {
        int height[] = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater.trap2(height);
    }
    @Test
    public void trap3Test() {
        int height[] = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater.trap3_2(height);
    }
}
