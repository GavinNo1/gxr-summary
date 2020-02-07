package alg;

import org.junit.Assert;
import org.junit.Test;

public class LongestSubstringWithoutRepeatingCharactersTest {

    @Test
    public void lengthOfLongestSubstringTest() {
        String s = "abcabcbb";
        int len = LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        Assert.assertEquals(len, 3);
    }
    @Test
    public void lengthOfLongestSubstringwithWindowTest() {
        String s = "abcabcbb";
        int len = LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstringWithWindow(s);
        Assert.assertEquals(len, 3);
    }
    @Test
    public void lengthOfLongestSubstringwithWindow2Test() {
        String s = "abcabcbb";
        int len = LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstringWithWindow2(s);
        Assert.assertEquals(len, 3);
    }
}
