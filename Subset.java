import java.util.ArrayList;
import java.util.List;

public class Subset {
    // no-duplicate and contains duplicate / 选或不选 or 顺序排列
    public List<String>  allSubsets(String set) {
        List<String> res = new ArrayList<>();
        helper(set, res, new StringBuilder(), 0);
        return res;
    }

    private void helper(String set, List<String> res, StringBuilder sb, int idx) {
        if (idx == set.length()) {
            res.add(sb.toString());
            return;
        }

        sb.append(set.charAt(idx));
        helper(set, res, sb, idx + 1);
        sb.deleteCharAt(sb.length() - 1);
        helper(set, res, sb, idx + 1);
    }
}
