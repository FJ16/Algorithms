import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kdistinct {
    public List<String> find(String input, int k) {
        List<String> res = new ArrayList<>();
        if (input == null || input.length() < k) return res;

        Set<String> dedup = new HashSet<>();
        boolean[] num = new boolean[26];
        int slow = 0;
        int fast = 0;

        while (fast < input.length()) {

            if (!num[input.charAt(fast) - 'a']) {
                num[input.charAt(fast) - 'a'] = true;
                fast++;

                if (fast - slow >= k) {
                    dedup.add(input.substring(slow, fast));
                    num[input.charAt(slow) - 'a'] = false;
                    slow++;
                }
            }
            else {
                while (num[input.charAt(fast) - 'a']) {
                    num[input.charAt(slow) - 'a'] = false;
                    slow++;
                }
            }
        }

        for (String cur : dedup) res.add(cur);
        return res;
    }
}
