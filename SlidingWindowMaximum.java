import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    // LC 239
    public int[] getRes(int[] input, int k) {
        if (input == null || input.length < k) return null;
        Deque<Integer> window = new LinkedList<>();

        int[] res = new int[input.length - k + 1]; // 长度相减 + 1 补回减去的元素
        //        0,1,2,3,4,5,6
        // e.g.   1,2,3,4,5,6 , k = 3
        //        6 - 3 = 3 --> arr[3] --> 转化成长度要 + 1

        for (int i = 0; i < input.length; i++) {
            while (!window.isEmpty() && input[i] >= input[window.peekLast()]) {
                // make sure we always have descending order in the Deque
                window.pollLast();
            }

            if (!window.isEmpty() && window.peekFirst() + k <= i) window.pollFirst();
            // i here 是准备进DQ的，如果 队列左侧的index + k(长度) 等于 当前i, 那么说明 最左侧的值已经达到window边界，需要出队

            window.offerLast(i);

            if (i >= k - 1) {
                // 第一个结果应该从第一次 window 扫过 k 个时输出,
                res[i - k + 1] = input[window.peekFirst()];
            }
        }

        return res;
    }
}
