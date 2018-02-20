import java.util.Deque;
import java.util.LinkedList;

public class BinarySearchTreeIterator {

    private final Deque<TreeNode> stk;

    public BinarySearchTreeIterator(TreeNode root) {
        stk = new LinkedList<TreeNode>();
        while (root != null) {
            stk.offerLast(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !stk.isEmpty();
    }

    public int next() {
        int res = -1;
        if (hasNext()) {
            res = stk.pollLast().val;
            TreeNode top = stk.peekLast();
            TreeNode right = top.right;
            while (right != null) {
                stk.offerLast(right);
                right = right.left;
            }
        }
        return res;
    }
}
