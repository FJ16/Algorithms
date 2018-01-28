import java.util.HashMap;
import java.util.Map;

public class LRUcache {
    // We need to consider the situations when there is a new data coming in.
    // 1 case : if we already have it (use the key to define), then we need to update the priority of the Cache hit op
    // 2 case : if not, then we have two more cases: a. if the cache is full, we need to delete and insert new data
    //   b. if the cache is not full, then we just need to put it in.
    // The next Question is "How can we store those key and value?", No doubt we need a map to store the key.
    // But how about the value? if we use another pre-defined collection data structure, that means we need to figure out another way to find the data in
    // addtional data structure, So we just need to 《Customize a Class》 for it (即是单独的数据结构，又可以链接成一个collection)
    // , which is better be a doubly linked list, since we can delete it and move it to the head or tail in Constant time

    private final Map<Integer, CacheNode> map;
    private final int cap;
    private CacheNode head; // for deleting least recently used cache node, 这里我们用 dummy head node 进行删除
    private CacheNode tail; // for inserting new or resent used cache node

    public LRUcache(int capacity) {
        map = new HashMap<Integer, CacheNode>();
        cap = capacity;
        head = new CacheNode(0,0); // dummy head
        tail = new CacheNode(0,0); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1; // corner 注意
        CacheNode res = map.get(key);
        // 断开当前 CacheNode, 再move to tail, 这里不写在 move to tail里是因为我们下面 cache miss也要调用move to tail
        res.prev.next = res.next;
        res.next.prev = res.prev;
        moveToTail(res);
        return res.val;
    }

    public void put(int key, int val) {
        // cache hit
        if (map.containsKey(key)) {
            CacheNode hit = map.get(key);
            hit.val = val;
            hit.prev.next = hit.next;
            hit.next.prev = hit.prev;
            moveToTail(hit);
        } else {
            // cache miss then add
            if (map.size() >= cap) {
                map.remove(head.next.key);
                head.next = head.next.next;
                head.next.prev = head;
                // or we can just move head to next;
                // head = head.next;
                // head.prev = null;
            }
            CacheNode add = new CacheNode(key, val);
            map.put(key, add); // 放到map里，保证O1时间查询
            moveToTail(add);  // 放到doubly linkedlist 进行判断是不是 least recent used
        }
    }

    private void moveToTail(CacheNode cur) {
        tail.prev.next = cur;
        cur.prev = tail.prev;

        cur.next = tail;
        tail.prev = cur;
    }


    class CacheNode {
        int key;
        int val;
        CacheNode prev;
        CacheNode next;
        CacheNode(int key, int val) {
            this.key = key;
            this.val = val;
        };
    }
}
