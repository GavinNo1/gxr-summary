package demo;

import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(5);
        TreeNode leftleft = new TreeNode(6);
        TreeNode leftright = new TreeNode(7);
        TreeNode rightleft = new TreeNode(8);
        TreeNode rightright = new TreeNode(9);
        left.left = leftleft;
        left.right = leftright;
        right.left = rightleft;
        right.right = rightright;
        root.left = left;
        root.right = right;
        Solution.inOrder(root);
//        System.err.println("***********"+root.val + root.left.val + root.right.val + root.left.left.val + root.left.right.val+ root.right.left.val + root.right.right.val);
        root = Solution.invertTree1(root);
        System.err.println("************");
//        System.err.println("***********"+root.val + root.left.val + root.right.val + root.left.left.val + root.left.right.val+ root.right.left.val + root.right.right.val);
        Solution.inOrder(root);
    }

    @Test
    public void test() {

        QueueTest.push("1");
        QueueTest.push("2");
        QueueTest.push("3");
        System.out.println(QueueTest.pop());  //返回3
        QueueTest.push("4");
        QueueTest.push("5");
        System.out.println(QueueTest.pop());  //返回5
        System.out.println(QueueTest.pop());  //返回4
        System.out.println(QueueTest.pop());  //返回2
        System.out.println(QueueTest.pop());  //返回1
    }
}
