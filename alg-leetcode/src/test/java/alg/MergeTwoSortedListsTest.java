package alg;

import base.ListNode;
import org.junit.Test;

public class MergeTwoSortedListsTest {
    @Test
    public void mergeTwoListsTest() {
        ListNode first = new ListNode(1);
        first.next = new ListNode(2);
        first.next.next = new ListNode(4);
        ListNode second = new ListNode(1);
        second.next = new ListNode(3);
        second.next.next = new ListNode(4);
        MergeTwoSortedLists.mergeTwoLists(first, second);
    }
}
