package alg;

import org.junit.Test;

public class ShortestUnsortedContinuousSubarrayTest {
    @Test
    public void findUnsortedSubarrayTest() {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        ShortestUnsortedContinuousSubarray.findUnsortedSubarray(nums);
    }
    @Test
    public void findUnsortedSubarrayTest2() {
        int[] nums = new int[]{2, 6, 1, 8, 10, 9, 15};
        ShortestUnsortedContinuousSubarray.findUnsortedSubarray2(nums);
    }
    @Test
    public void findUnsortedSubarrayTest3() {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        ShortestUnsortedContinuousSubarray.findUnsortedSubarray3(nums);
    }
}
