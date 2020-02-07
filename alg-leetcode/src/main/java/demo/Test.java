package demo;

public class Test {
    public static LinkedNode reverse(LinkedNode head) {
        LinkedNode node = new LinkedNode(0);
        while (head != null) {
            LinkedNode temp = head;
            LinkedNode next = head.next;
            temp.next = node.next;
            node.next = temp;
            head = next;
        }
        return node.next;
    }

    public static LinkedNode reverseGroup(LinkedNode head, int k) {
        LinkedNode temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp =temp.next;
        }
        if (temp.next == null) {
            return head;
        }
        LinkedNode t2 = temp.next;
        temp.next = null;
        LinkedNode newHead = reverse(head);
        LinkedNode newTemp = reverseGroup1(t2, k);
        head.next = newTemp;
        return newHead;
    }
    public static LinkedNode reverseGroup1(LinkedNode head, int k){
        LinkedNode temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp =temp.next;
        }
        if (temp.next == null) {
            return head;
        }
        temp.next = null;
        LinkedNode newHead = reverse(head);
        return newHead;
    }

}
