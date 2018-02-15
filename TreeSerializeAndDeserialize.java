import java.util.LinkedList;
import java.util.Queue;

public class TreeSerializeAndDeserialize {

    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder res = new StringBuilder();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        res.append(root.val);

        while (!que.isEmpty()) {
            TreeNode cur = que.poll();
            String left = cur.left == null ? "#" : String.valueOf(cur.left.val);
            String right = cur.right == null ? "#" : String.valueOf(cur.right.val);

            if (left != null) que.offer(cur.left);
            if (right != null) que.offer(cur.right);

            res.append(left + "," + right + ",");
        }
        res.deleteCharAt(res.length() - 1); // delete last remaining ","
        return res.toString();
    }

    public TreeNode deserialize(String input) {
        if (input == null || input.length() == 0) return null;
        String[] nodes = input.split(",");

        Queue<TreeNode> que = new LinkedList<>(); // 这里还是得用queue track TreeNode, 附带指针信息
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        que.offer(root);

        int idx = 1;
        while(!que.isEmpty()) {
            TreeNode cur = que.poll();

            if (!nodes[idx].equals("#")) {
                cur.left = new TreeNode(Integer.valueOf(nodes[idx]));
                que.offer(cur.left);
            }
            idx++;

            if (!nodes[idx].equals("#")) {
                cur.right = new TreeNode(Integer.valueOf(nodes[idx]));
                que.offer(cur.right);
            }
            idx++;

        }

        return root;
    }

    /* recursively implement those two functions */
    // This idea is simple, add "#" to indicate null node, so that the pre-order traversal can determine an unique tree structure

    private static int idx; // or we can use a queue to keep all the node info first and then process them one by one
    public String serializeR(TreeNode root) {
        if (root == null) return "#";

        String left = serializeR(root.left);
        String right = serializeR(root.right);
        return root.val + "," + left + "," + right; // always have two leaf node(null == "#") to non-null node
    }

    public TreeNode deserializeR(String input) {
        String[] nodes = input.split(",");
        return helper(nodes);
    }

    public TreeNode helper(String[] nodes) {
        if (nodes[idx].equals("#")) return null;

        TreeNode cur = new TreeNode(Integer.valueOf(nodes[idx]));
        idx++;
        cur.left = helper(nodes);
        idx++;
        cur.right = helper(nodes);
        return cur;
    }
}
