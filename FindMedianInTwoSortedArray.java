public class FindMedianInTwoSortedArray {
    // 谁小移谁, O(k)
    // binary search方法，每次扔掉肯定不符合要求的 k / 2 个不符合要求的 / 肯定不包含 kth元素的 部分
    public double findMedian(int[] a, int[] b) {
        int total = a.length + b.length;
        // since we need to find median --> k = total + 1 / 2;
        int k = (total + 1) / 2;
        // +1是为了满足当total是奇数时，除以2会"偏左"， 因为取得是int,
        // e.g. 5 / 2 = 2.5 --> 2, 但median位置应该是3，所以我们先 + 1， 保证能正确算到 median int, 且+1对偶数不影响结果，因为偶数 + 1后会"偏左"回来
        return helper(a, b, 0, 0, k, total);
    }
    // a_left & b_left represent the start position of our search range, [x_left, x_left + k]
    private double helper(int[] a, int[] b, int a_left, int b_left, int k, int total) {
        // base case 1: nothing left in one of those arrays
        if (a_left >= a.length) return total % 2 == 0? (double)(b[b_left + k - 1] + b[b_left + k]) / 2 : (double)b[b_left + k];
        if (b_left >= b.length) return total % 2 == 0? (double)(a[a_left + k - 1] + a[a_left + k]) / 2 : (double)a[a_left + k];

        // x_left + k - 1 是因为 <数组从0开始， 第k个的index对应 k - 1> !!!!

        // base case 2: k == 1 --> next one is what we want, or if total is even, we need find the Min and Second Min
        if (k == 1) {
            int min = Math.min(a[a_left], b[b_left]);
            if (total % 2 != 0) return min;

            // find second min value
            if (a[a_left] == b[b_left]) return (double)(a[a_left] + b[b_left]) / 2;
            else if (a[a_left] < b[b_left]) return a_left++;
            else b_left++;

            int next = Integer.MIN_VALUE;
            if (a_left < a.length && b_left < b.length) next = Math.min(a[a_left], b[b_left]);
            else if (a_left < a.length) next = a[a_left];
            else next = b[b_left];

            return (double)(min + next) / 2;
        }
        //
        int a_flag = a_left + k / 2 - 1 < a.length? a[a_left + k / 2 - 1] : Integer.MAX_VALUE;
        int b_flag = b_left + k / 2 - 1 < b.length? b[b_left + k / 2 - 1] : Integer.MAX_VALUE;

        // 每次扔掉 不可能存在 kth element 的 range, 这里 必须 要用 k - k / 2 来表示剩下的里面求第几个(因为已经弃掉 k / 2个，
        // 且 因为这里 用了除法 会有 "偏差"， 因此只能用减法..
        if (a_flag < b_flag) {
            return helper(a, b, a_left + k / 2, b_left, k - k / 2, total);
        } else {
            return helper(a, b, b_left, a_left + k / 2, k - k / 2, total);
        }
    }
}
