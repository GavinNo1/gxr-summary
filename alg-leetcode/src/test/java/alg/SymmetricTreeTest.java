package alg;

import base.TreeNode;
import org.junit.Test;

public class SymmetricTreeTest {

    @Test
    public void isSymmetric2Test() {
        TreeNode node = new TreeNode(1);
        TreeNode nodeLeft = new TreeNode(2);
        TreeNode nodeRight = new TreeNode(2);
        TreeNode nodeLeftLeft = new TreeNode(3);
        TreeNode nodeLeftRight = new TreeNode(4);
        TreeNode nodeRightLeft = new TreeNode(4);
        TreeNode nodeRightRight = new TreeNode(3);
        node.left = nodeLeft;
        node.left.left = nodeLeftLeft;
        node.left.right = nodeLeftRight;
        node.right = nodeRight;
        node.right.right = nodeRightRight;
        node.right.left = nodeRightLeft;
        SymmetricTree.isSymmetric2(node);
    }
}
