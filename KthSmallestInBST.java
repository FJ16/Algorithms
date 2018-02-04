public class KthSmallestInBST {
    public int kthSmallestInBST(TreeNode root, int k) {
        // DFS & Partition + Binary Search // 如果是Kth largest， 那么这种方法更好
        int leftNodeCount = countNode(root.left);

        if (k <= leftNodeCount) {
            return kthSmallestInBST(root.left, k);
        }
        else if (leftNodeCount + 1 == k) return root.val;
        else {
            return kthSmallestInBST(root.right, k - leftNodeCount - 1);
        }
    }

    private int countNode(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }

    // In-order Traversal
    public int kthSmallestInBSTInorder(TreeNode root, int k) {
        if (root == null) return -1;
        int[] count = new int[]{0, -1};
        helper(root, k, count);
        return count[0];
    }

    private void helper(TreeNode root, int k, int[] count) {
        if (root == null) return;

        helper(root.left, k, count);
        count[1]++;
        if (count[1] == k) count[0] = root.val;
        helper(root.right, k, count);
    }
}
