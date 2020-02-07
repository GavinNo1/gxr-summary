package alg;

import org.junit.Test;

public class LongestPalindromicSubstringTest {
    @Test
    public void longestPalindromeWithDynamicTest() {
        String s = "babad";
        String r = LongestPalindromicSubstring.longestPalindromeWithCenter(s);
        System.err.println(r);
    }
}
