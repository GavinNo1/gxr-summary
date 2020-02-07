package demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }


    public static TreeNode invertTree1(TreeNode root){
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null){
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }

    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.err.println(node.val+ "");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void breadthFirstTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        System.out.print("breadthFirstTraversal: ");
        while (!queue.isEmpty()) {
            TreeNode head = queue.remove();
            System.out.print(head.val + " ");
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
        }
        System.out.println("");
    }


    public static void preOrder1(TreeNode root) {
        if (root == null) {
            return;
        }
        System.err.println(root.val + "");
        if (root.left != null) {
            preOrder1(root.left);
        }
        if (root.right != null) {
            preOrder1(root.right);
        }
    }

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }


    public static LinkedNode reverseLinkedNode(LinkedNode head){
        return reverseList(head, null);

    }

    private static LinkedNode reverseList(LinkedNode head, LinkedNode newNode) {
        if (head == null) {
            return newNode;
        }
        LinkedNode next = head.next;
        head.next = newNode;
        newNode = head;
        return reverseList(next, newNode);
    }

    public static LinkedNode reverseList1(LinkedNode head) {
        //定义一个新的头部节点,每次反转的数据都会放在newHead的next节点位置
        LinkedNode newHead = new LinkedNode(0);
        while (head != null) {
            LinkedNode tmpHead = head;
            //记录next值，作为下次循环开始
            LinkedNode next = head.next;
            //新插入的元素的下一个节点要转变成newHead.next节点数据
            tmpHead.next = newHead.next;
            //将head节点和newHead.next节点合并后，作为下个节点放到newHead后面去
            newHead.next = tmpHead;
            head = next;
        }

        //newHead的next节点就是反转的链表数据
        return newHead.next;
    }

    public static LinkedNode reverseGroup(LinkedNode head, int k) {
        LinkedNode temp = head;
        for (int i = 1; i < k && temp != null; i++){
            temp = temp.next;
        }
        if (temp == null) {
            return head;
        }
        LinkedNode t2 = temp.next;
        temp.next = null;
        LinkedNode newHead = reverseList1(head);
        LinkedNode newTemp = reverseGroup(t2, k);
        head.next = newTemp;
        return newHead;
    }


    public static LinkedNode mergeTwoList(LinkedNode l1, LinkedNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
    }

    public static LinkedNode removeNthFromEnd(LinkedNode head, int n) {
        LinkedNode fast = head;
        LinkedNode slow = head;
        while (n-- > 0) {
            fast = fast.next;
        }

        if (fast == null) {
            return head.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }


}