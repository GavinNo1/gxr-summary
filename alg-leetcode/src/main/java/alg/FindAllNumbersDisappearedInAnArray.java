package alg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers2(int[] nums) {

        // Hash table for keeping track of the numbers in the array
        // Note that we can also use a set here since we are not
        // really concerned with the frequency of numbers.
        HashMap<Integer, Boolean> hashTable = new HashMap<Integer, Boolean>();

        // Add each of the numbers to the hash table
        for (int i = 0; i < nums.length; i++) {
            hashTable.put(nums[i], true);
        }

        // Response array that would contain the missing numbers
        List<Integer> result = new LinkedList<Integer>();

        // Iterate over the numbers from 1 to N and add all those
        // that don't appear in the hash table.
        for (int i = 1; i <= nums.length; i++) {
            if (!hashTable.containsKey(i)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * 原地修改
     * 我们需要知道数组中存在的数字，由于数组的元素取值范围是 [1, N]，所以我们可以不使用额外的空间去解决它。
     * 我们可以在输入数组本身以某种方式标记已访问过的数字，然后再找到缺失的数字
     * 算法
     * 遍历输入数组的每个元素一次。
     * 我们将把 |nums[i]|-1 索引位置的元素标记为负数。即 nums[|nums[i] |- 1] \times -1nums[∣nums[i]∣−1]×−1 。
     * 然后遍历数组，若当前数组元素 nums[i] 为负数，说明我们在数组中存在数字 i+1。
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        // Iterate over each of the elements in the original array
        for (int i = 0; i < nums.length; i++) {

            // Treat the value as the new index
            int newIndex = Math.abs(nums[i]) - 1;

            // Check the magnitude of value at this new index
            // If the magnitude is positive, make it negative
            // thus indicating that the number nums[i] has
            // appeared or has been visited.
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }

        // Response array that would contain the missing numbers
        List<Integer> result = new LinkedList<Integer>();

        // Iterate over the numbers from 1 to N and add all those
        // that have positive magnitude in the array
        for (int i = 1; i <= nums.length; i++) {

            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        FindAllNumbersDisappearedInAnArray disappearedNumbers = new FindAllNumbersDisappearedInAnArray();
        disappearedNumbers.findDisappearedNumbers(nums);
    }
}
