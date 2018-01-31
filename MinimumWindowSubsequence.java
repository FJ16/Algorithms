public class MinimumWindowSubsequence {
    public String minWindow(String S, String T) {
        // dp[i][j] represents the start index of the substring which ends at i-th character in longer string with the subsquence given by the substring ends at j-th character in short string.
        int ls = S.length();
        int lt = T.length();

        int[][] dp = new int[lt][ls];
        // fill our base cases, when we only have 1 character in short string to determine the subsquence
        for (int i = 0; i < ls; i++) {
            dp[0][i] = -1;
            if (S.charAt(i) == T.charAt(0)) dp[0][i] = i;
        }
        // fill the matrix
        for (int i = 1; i < lt; i++) {
            int start = -1; // we need to cache start index to determine if we should update the current valid result
            //这里也是我们的 base case,因为 i = 1, j = 0的时候，s还比t短，不可能满足t规定的subsequence, start必然无效, 所以默认-1(既第一列都-1)

            for (int j = 0; j < ls; j++) {
                dp[i][j] = -1; // default set the current result is -1(not find) and then check if we can find
                if (start != -1 && S.charAt(j) == T.charAt(i)) {  //我们有正确的Start idx， 且目前char相等，fix了current size的问题
                    dp[i][j] = start;  // 可以update当前结果
                }
                if (dp[i - 1][j] != -1) start = dp[i - 1][j]; //check if we have former valid substring, update start idx;
            }
        }

        // get the result
        int res = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < ls; i++) {
            if (dp[lt - 1][i] != -1) {
                int length = i - dp[lt - 1][i] + 1;
                if (length < res) {
                    res = length;
                    start = dp[lt - 1][i];
                    end = i;
                }
            }
        }

        return res == Integer.MAX_VALUE? "" : S.substring(start, end + 1);
    }
}
