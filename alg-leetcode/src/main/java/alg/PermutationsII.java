package alg;

import java.util.*;

/**
 *47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PermutationsII {
    /**
     * 在一定会产生重复结果集的地方剪枝
     * 我们可以在搜索之前就对候选数组排序，一旦发现这一支搜索下去可能搜索到重复的元素就停止搜索，这样结果集中不会包含重复元素
     * https://pic.leetcode-cn.com/0f1c183ceb7b634f8a527028afd4893e26dfe3796afce35cbb177b125939179b-LeetCode%20%E7%AC%AC%2047%20%E9%A2%98%EF%BC%9A%E2%80%9C%E5%85%A8%E6%8E%92%E5%88%97%20II%E2%80%9D%E9%A2%98%E8%A7%A3%E9%85%8D%E5%9B%BE.png
     * 产生重复结点的地方，正是图中标注了“剪刀”，且被绿色框框住的地方。
     * 大家也可以把第 2 个 1 加上 ' ，即 [1, 1', 2] 去想象这个搜索的过程。只要遇到起点一样，就有可能产生重复。这里还有一个很细节的地方：
     *
     * 1、在图中 ② 处，搜索的数也和上一次一样，但是上一次的 1 还在使用中；
     * 2、在图中 ① 处，搜索的数也和上一次一样，但是上一次的 1 刚刚被撤销，正是因为刚被撤销，下面的搜索中还会使用到，因此会产生重复，剪掉的就应该是这样的分支。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; ++i) {
            if (used[i]) {
                continue;
            }

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, len, depth + 1, used, path, res);
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        PermutationsII solution = new PermutationsII();
        int[] nums = {1, 1, 2};
        List<List<Integer>> res = solution.permuteUnique(nums);
        System.out.println(res);
    }

}
