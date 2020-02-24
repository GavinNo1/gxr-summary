package alg;

import org.junit.Test;

public class CurriculumTest {

    @Test
    public void canFinished() {
        int n = 2;
        int num[][] =  new int[][] {{1,0},{0,1}};
        Curriculum.canFinish(n, num);
    }
}
