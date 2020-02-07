package alg;

import base.ListNode;
import org.junit.Test;

public class MergeKListsTest {
    @Test
    public void mergeKListsTest(){
        ListNode first = new ListNode(1);
        first.next = new ListNode(4);
        first.next.next = new ListNode(5);
        ListNode second = new ListNode(1);
        second.next = new ListNode(3);
        second.next.next = new ListNode(4);
        ListNode three = new ListNode(2);
        three.next = new ListNode(6);
        ListNode[] listNodes = new ListNode[]{first, second, three};
        ListNode node = MergeKLists.mergeKLists2(listNodes);
        System.err.println();
    }
}
