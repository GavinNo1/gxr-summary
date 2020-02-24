package alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTheDuplicateNumber {
    /**
     * 算法：
     * 算法相当简单。首先，我们对数组进行排序，然后将每个元素与前一个元素进行比较。因为数组中只有一个重复的元素，所以我们知道数组的长度至少为 2，一旦找到重复的元素，我们就可以返回它。
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }

    /**
     * 集合
     * 如果我们在数组上迭代时存储每个元素，我们可以在数组上迭代时简单地检查每个元素
     * 算法：
     * 为了实现线性时间复杂性，我们需要能够在恒定时间内将元素插入数据结构（并查找它们）。set 很好地满足这些约束，所以我们迭代数组并将每个元素插入 seen 中。在插入之前，我们检查它是否已经存在。如果是，那么我们找到了我们的副本，所以我们返回它
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }
}
