package alg;

import org.junit.Test;

public class SearchRangeTest {

    @Test
    public void searchRangeTest() {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 9;
        SearchRange.searchRange(nums, target);
    }
}
