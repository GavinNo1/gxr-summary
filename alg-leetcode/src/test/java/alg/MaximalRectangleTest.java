package alg;

import org.junit.Test;

public class MaximalRectangleTest {

    @Test
    public void maximalRectangleTest() {
        int[][] rect = new int[][] {{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        MaximalRectangle.maximalRectangle(rect);
    }
}
