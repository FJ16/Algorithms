import java.util.List;

public class NaryTreeSerializaAndDeserialize {
    public String serialize(NAryNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        dfsHelper(sb, root);

        return sb.toString();
    }

    private void dfsHelper(StringBuilder sb, NAryNode root) {
        if (root == null) {
            return;
        }

//        sb.append("(");// 可以省一些空间
        sb.append(root.label);
        for (NAryNode child : root.children) {
            dfsHelper(sb, child);
        }
        sb.append(")");
    }

    int index = 0;
    public NAryNode deserialize(String input) {
        if (input == null || input.isEmpty() || index >= input.length()) {
            return null;
        }

//        if (input.charAt(index) == '(') {//省了空间以后就不用跳过'('了
//            index++;// skip '('
        NAryNode node = new NAryNode(input.charAt(index));// create node
        index++;// go to next char
        while (input.charAt(index) != ')') { // if not == ')', has child
            node.children.add(deserialize(input));// so create child node recursively
        }
        index++;// no child / finished creating child go to next char
        return node;
//        }
//        return null;

    }

    public static void main(String[] args) {
        NaryTreeSerializaAndDeserialize sol = new NaryTreeSerializaAndDeserialize();

        NAryNode a = new NAryNode('a');
        NAryNode b = new NAryNode('b');
        NAryNode c = new NAryNode('c');
        NAryNode d = new NAryNode('d');
        NAryNode e = new NAryNode('e');
        NAryNode f = new NAryNode('f');
        NAryNode g = new NAryNode('g');
        NAryNode h = new NAryNode('h');
        NAryNode i = new NAryNode('i');
        NAryNode j = new NAryNode('j');
        NAryNode k = new NAryNode('k');

        a.children.add(b);
        a.children.add(c);
        a.children.add(d);

        b.children.add(e);
        b.children.add(f);

        f.children.add(k);

        d.children.add(g);
        d.children.add(h);
        d.children.add(i);
        d.children.add(j);

        String input = sol.serialize(a);
        System.out.println(input);
        NAryNode res = sol.deserialize(input);
        // 省空间的输出：abe)fk)))c)dg)h)i)j)))
        // 不省的输出：(a(b(e)(f(k)))(c)(d(g)(h)(i)(j)))
    }
}

class NAryNode {
    char label;
    List<NAryNode> children;
    NAryNode (char label) {
        this.label = label;
    }
}
