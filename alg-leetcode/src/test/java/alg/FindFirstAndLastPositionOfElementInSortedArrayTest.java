package alg;

import org.junit.Test;

public class FindFirstAndLastPositionOfElementInSortedArrayTest {

    @Test
    public void searchRangeTest() {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 9;
        FindFirstAndLastPositionOfElementInSortedArray.searchRange(nums, target);
    }
}
