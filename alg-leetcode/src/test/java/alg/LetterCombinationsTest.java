package alg;

import base.ListNode;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class LetterCombinationsTest {
    @Test
    public void letterCombinationsTest() {
        List<String> list = LetterCombinations.letterCombinationsWithQueue("234");
        System.err.println(JSON.toJSON(list));
    }
}
