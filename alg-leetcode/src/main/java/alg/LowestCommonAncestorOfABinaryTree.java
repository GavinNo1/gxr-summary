package alg;

import base.TreeNode;

import java.util.*;

/**
 * 236. 二叉树的最近公共祖先
 */
public class LowestCommonAncestorOfABinaryTree {
    /**
     * 使用父指针迭代
     * 如果每个节点都有父指针，那么我们可以从 p 和 q 返回以获取它们的祖先。在这个遍历过程中，我们得到的第一个公共节点是 LCA 节点。我们可以在遍历树时将父指针保存在字典中。
     * 算法
     * 从根节点开始遍历树。
     * 在找到 p 和 q 之前，将父指针存储在字典中。
     * 一旦我们找到了 p 和 q，我们就可以使用父亲字典获得 p 的所有祖先，并添加到一个称为祖先的集合中。
     * 同样，我们遍历节点 q 的祖先。如果祖先存在于为 p 设置的祖先中，这意味着这是 p 和 q 之间的第一个共同祖先（同时向上遍历），因此这是 LCA 节点。
     * @param root
     * @param p
     * @param q
     * @return
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(1);
        TreeNode leftleft = new TreeNode(6);
        TreeNode leftright = new TreeNode(2);
        TreeNode rightleft = new TreeNode(0);
        TreeNode rightright = new TreeNode(8);
        node.left = left;
        node.right = right;
        node.left.left =leftleft;
        node.left.right =leftright;
        node.right.left =rightleft;
        node.right.right =rightright;
        LowestCommonAncestorOfABinaryTree lowestCommonAncestorOfABinaryTree = new LowestCommonAncestorOfABinaryTree();
        lowestCommonAncestorOfABinaryTree.lowestCommonAncestor(node, rightleft, rightright);
    }
}
