package alg;

import org.junit.Test;

public class MaxiMumSubarrayTest {

    @Test
    public void maxSubArrayTest() {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaxiMumSubarray.maxSubArray2(nums);
    }
}
