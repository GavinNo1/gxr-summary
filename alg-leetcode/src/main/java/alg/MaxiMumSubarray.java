package alg;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxiMumSubarray {
    /**
     * 贪心
     * 使用单个数组作为输入来查找最大（或最小）元素（或总和）的问题，贪心算法是可以在线性时间解决的方法之一。
     * 每一步都选择最佳方案，到最后就是全局最优的方案
     * 算法：
     * 该算法通用且简单：遍历数组并在每个步骤中更新：
     *
     * 当前元素
     * 当前元素位置的最大和
     * 迄今为止的最大和
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            //curr max sum
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    /**
     * 动态规划
     * 算法：
     *
     * 在整个数组或在固定大小的滑动窗口中找到总和或最大值或最小值的问题可以通过动态规划（DP）在线性时间内解决。
     * 有两种标准 DP 方法适用于数组：
     * 常数空间，沿数组移动并在原数组修改。
     * 线性空间，首先沿 left->right 方向移动，然后再沿 right->left 方向移动。 合并结果。
     * 我们在这里使用第一种方法，因为可以修改数组跟踪当前位置的最大和。
     * 下一步是在知道当前位置的最大和后更新全局最大和.
     * https://pic.leetcode-cn.com/e6ca21377d5533204c3149e0b5cdcc146ada4efe1ed2294b3e0615cdb4754853-file_1576478143560
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for(int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaxiMumSubarray.maxSubArray2(nums);
    }
}
