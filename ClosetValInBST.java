public class ClosetValInBST {
    public int closestValue(TreeNode root, double target) {
        // Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right; // 产生结果方向
        if (kid == null) return a; // 方向上没有node,则直接返回当前
        int b = closestValue(kid, target); // 否则在方向上找下面理我最近的node <recursion问题同方式缩小>
        return Math.abs(a - target) < Math.abs(b - target) ? a : b; //比较当前和下面两个最近node,返回最近值
    }

     public int closestValue_MySolution(TreeNode root, double target) {
         double[] res = new double[]{0, Double.MAX_VALUE};
         helper(root, target, res);
         return (int) res[0];
     }

     private void helper(TreeNode root, double target, double[] res) {
         if (root == null) return;

         double cur = (double) root.val;
         double diff = Math.abs(target - cur);
         if (diff < res[1]) {
             res[0] = cur;
             res[1] = diff;
         }

         if (target > cur) helper(root.right, target, res);
         else if (target < cur) helper(root.left, target, res);
         else {
             res[0] = cur;
         }
         return;
     }
}
