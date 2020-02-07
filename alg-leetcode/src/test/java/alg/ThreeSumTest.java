package alg;

import org.junit.Test;

import java.util.List;

public class ThreeSumTest {
    @Test
    public void threeSumTest() {
        int[] arr = new int[]{-1,0,1,2,-1,-1,-4};
        List<List<Integer>> lists = ThreeSum.threeSum(arr);
        System.err.println();
    }
}
