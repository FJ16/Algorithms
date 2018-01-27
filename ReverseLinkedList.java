public class ReverseLinkedList {
    // iteratively
    public ListNode iterativelyRvrs(ListNode head) {
        if (head == null) return head;
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    // recursively
    public ListNode recursively(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = recursively(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
