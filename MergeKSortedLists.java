import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public ListNode mergeK (ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(12, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        for (ListNode cur : lists) {
            minHeap.offer(cur);
        }

        while (!minHeap.isEmpty()) {
            ListNode next = minHeap.poll();

            // check null !!
            if (next.next != null)
                minHeap.offer(next.next);

            dummy.next = next;
            dummy = dummy.next;
        }

        return head.next;
    }
}
