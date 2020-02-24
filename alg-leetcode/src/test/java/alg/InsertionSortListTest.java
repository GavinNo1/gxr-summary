package alg;

import base.ListNode;
import org.junit.Test;

public class InsertionSortListTest {
    @Test
    public void insertionSortListTest(){
        ListNode node = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        node.next =node1;
        node1.next = node2;
        node2.next = node3;
        ListNode node4 = InsertionSortList.insertionSortList(node);
        System.err.println(node4);
    }
}
