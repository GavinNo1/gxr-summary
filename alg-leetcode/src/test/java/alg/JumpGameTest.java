package alg;

import org.junit.Test;

public class JumpGameTest {

    @Test
    public void canJumpTest() {
//        int[] nums = new int[]{2,3,1,1,4};
        int[] nums = new int[]{3,2,1,0,4};
        JumpGame.canJump4(nums);
    }
}
