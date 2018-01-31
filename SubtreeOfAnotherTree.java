public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return helper(s, t, false);
    }

    private boolean helper(TreeNode s, TreeNode t, boolean find) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        boolean validHere = false;
        if (s.val == t.val) {
            validHere = helper(s.left, t.left, true) && helper(s.right, t.right, true);
        }
        if (find && s.val != t.val) return false;
        return validHere || helper(s.left, t, find) || helper(s.right, t, find);
    }
}
