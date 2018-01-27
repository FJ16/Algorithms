public class ValidateBST {
    // 两种写法
    // inorder 遍历
    TreeNode prev = null; // keep tracking a prev visited node in xx-order traversal
    public boolean isValid(TreeNode root) {
        if (root == null) return true;

        if (isValid(root.left)) {
            // update prev when the cur one is in the in-order sequence
            if (prev != null && prev.val > root.val) return false;
            prev = root;
            return isValid(root.right);
        }
        else return false;
    }

    // keep min and max Node(TreeNode), 使用 Node是因为我们可以用 null 值做边界，来排除 input tree里
    // 有最大最小值的情况
    // 另外，如果要处理 等于 也 valid的情况，那么只能使用方法2

    public boolean isValid2(TreeNode root, TreeNode max, TreeNode min) {
        if (root == null) return true;

        if((max != null && root.val >= max.val) || (min != null && root.val <= min.val)) return false;
        return isValid2(root.left, root, min) && isValid2(root.right, root, max);
    }
}
