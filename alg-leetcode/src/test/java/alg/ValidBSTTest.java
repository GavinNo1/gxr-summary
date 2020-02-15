package alg;

import base.TreeNode;
import org.junit.Test;

public class ValidBSTTest {
    @Test
    public void isValidBST() {
        TreeNode node = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        node.left = left;
        node.right = right;
        ValidBST.isValidBST3(node);
    }
}
