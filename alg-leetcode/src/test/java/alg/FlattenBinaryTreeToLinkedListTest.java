package alg;

import base.TreeNode;
import org.junit.Test;

public class FlattenBinaryTreeToLinkedListTest {
    @Test
    public void flattenTest() {
        TreeNode node = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(5);
        TreeNode leftleft = new TreeNode(3);
        TreeNode leftright = new TreeNode(4);
        TreeNode rightright = new TreeNode(6);
        node.left = left;
        node.right = right;
        node.left.left = leftleft;
        node.left.right = leftright;
        node.right.right = rightright;
        FlattenBinaryTreeToLinkedList.flatten(node);
    }
}
