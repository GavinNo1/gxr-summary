package alg;

/**
 * 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HammingDistance {
    /**
     * 异或运算：相同的为0，不同的为1
     * 将两个数做异或运算，然后计算1的个数即是答案。
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * 将x，y按位异或得到i，将问题转化为求i的二进制位中1的个数count
     * 当i不为0时，将i与1按位与，判断二进制末尾是不是1，是，count++
     * 将i右位移一位
     * 重复第二，第三步，直到第二步条件不满足，，即i==0时终止统计， 即可得到i的二进制位中1的个数，问题得解
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance2(int x, int y) {
        int i = x ^ y;
        int count = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                count++;
            }
            i = i >> 1;
        }
        return count;
    }

}
