package alg;

import base.TreeNode;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DiameterOfBinaryTree {

    int ans;

    /**
     * 深度优先搜索
     * 想法
     * 任意一条路径可以被写成两个 箭头（不同方向），每个箭头代表一条从某些点向下遍历到孩子节点的路径。
     * 假设我们知道对于每个节点最长箭头距离分别为 L,R，那么最优路径经过 L + R + 1 个节点。
     * 算法
     * 按照常用方法计算一个节点的深度：max(depth of node.left, depth of node.right) + 1。在计算的同时，经过这个节点的路径长度为 1 + (depth of node.left) + (depth of node.right) 。搜索每个节点并记录这些路径经过的点数最大值，期望长度是结果 - 1。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L+R+1);
        return Math.max(L, R) + 1;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftleft = new TreeNode(4);
        TreeNode leftright = new TreeNode(5);
        node.left = left;
        node.right = right;
        node.left.left = leftleft;
        node.left.right = leftright;
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        diameterOfBinaryTree.diameterOfBinaryTree(node);

    }
}
