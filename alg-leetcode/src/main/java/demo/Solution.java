package demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {



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