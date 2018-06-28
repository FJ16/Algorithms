public class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        // corner cases
        if (nums == null || nums.length == 0) return 0;

        int max = 0; int curSum = 0;
        int left = 0; int right = 0;
        // right is the split point that triggers the current element is going to be added or not
        while (right < nums.length) {
            while (curSum > k) {
                curSum -= nums[left++];
            }

            curSum += nums[right++];
            if (right - left > max && curSum == k) max = right - left;
        }

        return max;
    }
}
