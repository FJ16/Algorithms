public class DeleteNodeInBST {
    public TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val < key) { // BST， target only locates at "one" side
            root.right = delete(root.right, key);
        }
        else if (root.val > key) {
            root.left = delete(root.left, key); // !!!use current pointers to delete(gain return for updating the child node
        }
        else {
            if (root.right.left == null) { // 如果我们想挪右边的node上来，但如果我们 right subtree没有左子节点，可以直接全移过来
                root.right.left = root.left;
                return root.right;
            }
            else {
                TreeNode newOne = findMin(root.right);
                newOne.left = root.left;
                newOne.right = root.right;
                return newOne;
            }
        }
        return root;
    }

    private TreeNode findMin(TreeNode cur) {
        TreeNode prev = cur;
        while (cur.left != null) {
            prev = cur;
            cur = cur.left;
        }

        prev.left = cur.right;
        return cur;
    }
}
