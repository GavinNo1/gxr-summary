package alg;

import java.util.*;

/**
 * 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordBreak {
    /**
     * 使用动态规划
     * 这个方法的想法是对于给定的字符串（s）可以被拆分成子问题 s1 和 s2 。如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题 s 也可以满足。也就是，如果 "catsanddog" 可以拆分成两个子字符串 "catsand" 和 "dog" 。子问题 "catsand" 可以进一步拆分成 "cats" 和 "and" ，这两个独立的部分都是字典的一部分，所以 "catsand" 满足题意条件，再往前， "catsand" 和 "dog" 也分别满足条件，所以整个字符串 "catsanddog" 也满足条件。
     *
     * 现在，我们考虑 dp 数组求解的过程。我们使用 n+1 大小数组的dp ，其中 n 是给定字符串的长度。我们也使用 2 个下标指针 i 和 j ，其中 i 是当前字符串从头开始的子字符串（s'）的长度， j是当前子字符串（s')的拆分位置，拆分成 s'(0,j)和 s'(j+1,i)
     *
     * 为了求出 dp 数组，我们初始化 dp[0] 为true ，这是因为空字符串总是字典的一部分。 dp 数组剩余的元素都初始化为 false 。
     *
     * 我们用下标 i 来考虑所有从当前字符串开始的可能的子字符串。对于每一个子字符串，我们通过下标 j 将它拆分成 s1'和 s2'（注意 i 现在指向 s2'的结尾）。为了将 dp[i] 数组求出来，我们依次检查每个dp[j] 是否为true ，也就是子字符串 s1'是否满足题目要求。如果满足，我们接下来检查 s2'是否在字典中。如果包含，我们接下来检查 s2'是否在字典中，如果两个字符串都满足要求，我们让 dp[i] 为true ，否则令其为 false 。
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 使用宽度优先搜索
     * 另一个方法是使用宽度优先搜索。将字符串可视化成一棵树，每一个节点是用 end 为结尾的前缀字符串。当两个节点之间的所有节点都对应了字典中一个有效字符串时，两个节点可以被连接。
     *
     * 为了形成这样的一棵树，我们从给定字符串的第一个字符开始（比方说 s ），将它作为树的根部，开始找所有可行的以该字符为首字符的可行子串。进一步的，将每一个子字符串的结束字符的下标（比方说 i）放在队列的尾部供宽搜后续使用。
     *
     * 每次我们从队列最前面弹出一个元素，并考虑字符串 s(i+1,end) 作为原始字符串，并将当前节点作为树的根。这个过程会一直重复，直到队列中没有元素。如果字符串最后的元素可以作为树的一个节点，这意味着初始字符串可以被拆分成多个给定字典中的子字符串。
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }
}
