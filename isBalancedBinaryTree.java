public class isBalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (null == root) return true;
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode root){
        if (root == null) return 0;

        int left = getHeight(root.left);
        if (left == -1) return -1;
        int right = getHeight(root.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
