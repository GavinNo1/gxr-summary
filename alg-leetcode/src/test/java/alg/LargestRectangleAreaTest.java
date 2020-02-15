package alg;

import org.junit.Test;

public class LargestRectangleAreaTest {
    @Test
    public void largestRectangleAreaTest() {
        int[] nums = new int[]{2,1,5,6,2,3};
//        int[] nums = new int[]{6, 4, 5, 2, 4, 3, 9};
        int max = LargestRectangleArea.largestRectangleArea2(nums);
        System.err.println(max);
    }
}
