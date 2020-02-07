package alg;

import org.junit.Test;

public class NextPermutationTest {
    @Test
    public void nextPermutation() {
//        int[] num = new int[]{1,5,8,4,7,6,5,3,1};
//        int[] num = new int[]{1,2,3,4};
        int[] num = new int[]{5,4,3,2,1};
        NextPermutation.nextPermutation(num);
    }
}
