package alg;

import base.ListNode;

public class ReverseLinkedList {

    public static ListNode reverseList(ListNode head) {
        //定义一个新的头部节点,每次反转的数据都会放在newHead的next节点位置
        ListNode newHead = new ListNode(0);
        while (head != null) {
            ListNode tmpHead = head;
            //记录next值，作为下次循环开始
            ListNode next = head.next;
            //新插入的元素的下一个节点要转变成newHead.next节点数据
            tmpHead.next = newHead.next;
            //将head节点和newHead.next节点合并后，作为下个节点放到newHead后面去
            newHead.next = tmpHead;
            head = next;
        }

        //newHead的next节点就是反转的链表数据
        return newHead.next;
    }
}
