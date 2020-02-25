package alg;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class LetterCombinationsOfAPhoneNumberTest {
    @Test
    public void letterCombinationsTest() {
        List<String> list = LetterCombinationsOfAPhoneNumber.letterCombinationsWithQueue("234");
        System.err.println(JSON.toJSON(list));
    }
}
