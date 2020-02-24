package alg;

import org.junit.Test;

public class MaximalSquareTest {
    @Test
    public void maximalSquareTest() {
        int[][] matrix = new int [][]{{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        MaximalSquare.maximalSquare(matrix);
    }
}
