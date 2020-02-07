package alg;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class GenerateParenthesisTest {
    @Test
    public void generateParenthesisTest() {
        List<String> list =  GenerateParenthesis.generateParenthesisWithQueue(2);
        System.err.println(JSON.toJSONString(list));
    }
}
