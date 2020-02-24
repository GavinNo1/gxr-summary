package alg;

/**
 * 647. 回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 *   输入: “babad”
 *   输出: “bab”
 *   注意: “aba” 也是一个有效答案。
 * 示例 2：
 *
 *   输入: “cbbd”
 *   输出: “bb”
 */
public class LongestPalindromicSubstring {
    /**
     * 中心扩展算法
     *   中心扩展就是把给定的字符串的每一个字母或两个字母之间空隙当做中心，向两边扩展，这样来找
     *
     *   长度为奇数的回文串，比如a, aba, abcba，以字母为中心
     *   长度为偶数的回文串，比如aa, abba，以两个字母之间空隙为中心
     * @param s
     * @return
     */
    public static String longestPalindromeWithCenter(String s) {

        if(null == s || s.length() <= 1){
            return s;
        }

        // 记录回文子串的开始位置
        int start =0;
        // 记录回文子串的结束位置
        int end = 0;

        for(int i=0; i<s.length(); i++){
            // 以每个字符为中心去扩展，例如"aba"就是以'b'为中心
            int len1 = expandAroundCenter(s,i,i);
            // 以两字母之间为中心去扩展，例如 "abba" 的中心在两个 'b'之间
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);

            if(len > end -start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start,end+1);
    }

    /**
     * 找到以left和right为中心的最大回文串长度
     * @param s
     * @param left
     * @param right
     * @return
     */
    private static int expandAroundCenter(String s, int left, int right) {
        while(left >=0 && right<s.length() && s.charAt(left) == s.charAt(right)){
            left -- ;
            right ++;
        }

        return right - left - 1;
    }


    public static boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     *暴力破解
     * 列举所有的子串，判断是否为回文串，保存最长的回文串
      */
    public static String longestPalindromeWithDirect(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    /**
     * 在动态规划的思想中，总是希望把问题划分成相关联的子问题；然后从最基本的子问题出发来推导较大的子问题，直到所有的子问题都解决。
     *
     *   假设字符串s的长度为length，建立一个length*length的矩阵dp。令 dp[i][j] 表示 S[i] 至 S[j] 所表示的子串是否是回文子串。
     *
     * 当 i == j，dp[i][j] 是回文子串（单字符都是回文子串）；
     * 当 j - i < 3，只要 S[i] == S[j]，则 dp[i][j] 是回文子串（如"aa"，“aba”），否则不是；
     * 当 j - i >= 3，如果 S[i] == S[j] && dp[i+1][j-1] ，则 dp[i][j] 是回文子串，否则不是 。
     * 状态方程
     *
     *              true   i == j
     *
     * dp[i][j] =   S[i] == S[j] j - i < 3
     *
     *              S[i] == S[j] && dp[i+1][j-1]  j - i >= 3
     *
     *需要注意的点是，因为要访问dp[i+1][j-1]，因此 i 是从大到小的，j是从小到大的。
     * @param s
     * @return
     */
    public static String longestPalindromeWithDynamic(String s) {

        if (null == s || s.length() <= 1) {
            return s;
        }

        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int left = 0;
        int right = 0;

        for (int i = len - 1; i >= 0; i--) {
            // 将对角线（即i==j的情况）赋值为true
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {

                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && right - left < j - i) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }


}
