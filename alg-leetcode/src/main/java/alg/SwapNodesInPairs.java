package alg;

import base.ListNode;

/**
 * 两两交换链表中的节点
 */
public class SwapNodesInPairs {

    public static ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while(temp.next != null && temp.next.next != null) {
            // 获取第一个节点
            ListNode start = temp.next;
            // 获取第二个节点
            ListNode end = temp.next.next;
            //开始交换节点
            start.next = end.next;
            end.next = start;
            //将交换完成的节点保存
            temp.next = end;
            // 开启下一次交换
            temp = start;
        }
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        swapPairs(node1);
    }
}
