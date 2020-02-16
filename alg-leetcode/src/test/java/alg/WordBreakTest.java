package alg;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class WordBreakTest {

    @Test
    public void wordBreakTest() {
//        String s = "catsandog";
//        List list = Arrays.asList("cats", "dog", "sand", "and", "cat");
        String s = "applepenapple";
        List list = Arrays.asList("apple", "pen");
        WordBreak.wordBreak(s, list);
    }
}
