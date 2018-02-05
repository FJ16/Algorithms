public class KthSmallestInTwoSortedArrays {
    public int kthSmallest(int[] a, int[] b, int k) {
        // 肯定先从谁小移谁开始想起
        return helper(a, b, 0, 0, k);
    }

    private int helper(int[] a, int[] b, int a_left, int b_left, int k) {
        // base case 1:
        if (a_left >= a.length) return b[b_left + k - 1];
        if (b_left >= b.length) return a[a_left + k - 1];

        // base cese 2:
        if (k == 1) {
            return Math.min(a[a_left], b[b_left]);
        }

        int a_flag = a_left + k / 2 - 1 < a.length? a_left + k / 2 - 1 : Integer.MAX_VALUE;
        int b_flag = b_left + k / 2 - 1 < b.length? b_left + k / 2 - 1 : Integer.MIN_VALUE;

        if (a_flag <= b_flag) {
            return helper(a, b, a_left + k / 2, b_left, k - k / 2);
        } else {
            return helper(a, b, a_left, b_left + k / 2, k - k / 2);
        }
    }
}
