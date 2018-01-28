public class MinimumSizeSubarraySum {
    public int lc209(int[] input, int s) {
        // n positive number in input, s is also a positive number
        if (input == null || input.length == 0) return 0;

        int slow = 0;
        int fast = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (slow <= fast && fast < input.length) { // fast不能出界
            if (sum + input[fast] < s) { //明确window的意义，同时也注意开闭规则，这里如果[), 那么
                sum += input[fast++];
            } else {
                res = Math.min(res, fast - slow + 1);
                sum -= input[slow++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
