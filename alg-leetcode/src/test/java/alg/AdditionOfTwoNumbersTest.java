package alg;

import base.ListNode;
import org.junit.Test;

public class AdditionOfTwoNumbersTest {
    @Test
    public void test () {
        ListNode first = new ListNode(2);
        first.next = new ListNode(4);
        first.next.next = new ListNode(3);
        ListNode second = new ListNode(5);
        second.next = new ListNode(6);
        second.next.next = new ListNode(4);
        ListNode node = AdditionOfTwoNumbers.addTwoNumbers(first, second);
        System.err.println(node.val);
        System.err.println(node.next.val);
        System.err.println(node.next.next.val);
    }
}
