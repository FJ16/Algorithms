import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) dict.add(word);

        // dp[i]: if the word with LENGTH i (不包含 s.charAt(i)这个 character)can be breaked by the wordDict;
        boolean[] dp = new boolean[s.length() + 1]; // 因为我们希望长度能在里面存，所以 +1 把长度包含进去
        dp[0] = true;
        // dp[0] represent the string is empty, which means we never cut it,
        // so it's true that we can determine the whole string is in the dictionary or not

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public List<String> wordBreakII(String s, List<String> wordDict) {

        return new ArrayList<String>();
    }
}
