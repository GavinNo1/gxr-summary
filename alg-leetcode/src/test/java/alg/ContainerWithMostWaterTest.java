package alg;

import org.junit.Test;

public class ContainerWithMostWaterTest {

    @Test
    public void maxAreaWithDirectTest() {
        int[] height = new int[] {1,8,6,2,5,4,8,3,7};
        int max = ContainerWithMostWater.maxAreaWithDirect(height);
        System.err.println(max);
    }
}
