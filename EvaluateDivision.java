import java.util.HashMap;
import java.util.Map;

public class EvaluateDivision {
    // DFS见Leetcode

    // 重点是 Union-Find 怎么写 -- 这里推荐算法哥套路(OOP) --> 【时间复杂度 / 空间复杂度】

    // Node class for the graph, generally we can also use array/matrix or HashMap to build, too
    // if we are using array/matrix or Map to construct the whole graph, create a Union Find class to contain them !
    private class Num {
        private String tag; //public ok ?
        private double val;
        // ！！！Where this val is the denominator --> 村长val / 该村民val, 所以在Query 村民A和村民B时，消掉村长就可以了
        private Num representative;
        private int size;

        Num (String tag) {
            this.tag = tag;
            val = 1.0; //自己是自己的村长, x / x = 1
            this.representative = this;
            size = 1;
        }
    }

    // Find
    private boolean find(Num a, Num b) { //找村长and更新村长(root)
        return root(a) == root(b);
    }

    // Union
    private void union(Num a, Num b, double d) {
        // where d == a / b

        // union their representative, ONLY 村长，这就是为什么我们在 root时，
        // 要更新没有被更新过的村民，使他们的val和parent指针更新成新村长，因为只是村长union了
        Num r1 = root(a);
        Num r2 = root(b);
        if (r1.size > r2.size) {
            r2.representative = r1;
            r1.size += r2.size; // always union smaller group to larger one, lower chance to update 村民
            r2.val = (a.val * d) / b.val;
            // where a.val == root1 / a's self value, b.val == root2.val / b's self value,
            // because we are going to union 村长 to point to 村长1, then we should have:
            //  root2.val = root1's self value / root2's self value =
            //  root1    root2    a    root1
            //  -----  / ----- * --- = -----
            //    a        b      b    root2
        } else {
            r1.representative = r2;
            r2.size += r1.size;
            r1.val = b.val / (a.val * d);
            //  root2    root1    a    root2
            //  -----  / ----- / --- = -----
            //    b        a      b    root1
        }
    }

    private Num root(Num num) {
        // Not only find 村长，还要注意更新村民的值，因为UNION-FIND就是应该让 所有村民Node都尽可能指向村长Node
        // (也可以连指，但是要计算结果早晚要更新/连乘)
        Num cur = num;
        double d = 1.0;
        while (cur != cur.representative) {
            // if cur node is not a 村长
            cur.val *= cur.representative.val;
            // 如果已经指向村长，那么这里只是 乘1， 如果还没指向村长，则e.g.   a / b (b -> a) *  c / a (a -> c) = c / b (b -> c 新村长)
            d *= cur.val; // d值应该是指向 正确村长 后的值

            cur.representative = cur.representative.representative; // 换村长
            cur = cur.representative; // move forward to check if 村长更新没
        }
        num.representative = cur;
        num.val = d;
        return cur;
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        double[] ret = new double[queries.length];
        Map<String, Num> unionGraph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String numerator = equations[i][0];
            String denominator = equations[i][1];
            if (!unionGraph.containsKey(numerator)) {
                unionGraph.put(numerator, new Num(numerator));
            }
            if (!unionGraph.containsKey(denominator)) {
                unionGraph.put(denominator, new Num(denominator));
            }

            Num diviend = unionGraph.get(numerator);
            Num divisor = unionGraph.get(denominator);
            // find -> union
            if (!find(diviend, divisor)) {
                union(diviend, divisor, values[i]);
            }
        }

        for (int i = 0; i < queries.length; i++) {

            //corner cases: Remember to clarify when starting
            String[] query = queries[i];
            if (!unionGraph.containsKey(query[0]) || !unionGraph.containsKey(query[1])) {
                ret[i] = -1.0;
            } else {
                Num root1 = unionGraph.get(query[0]);
                Num root2 = unionGraph.get(query[1]);
                if (find(root1, root2)) { //找村长and更新村长
                    ret[i] = root2.val / root1.val;
                } else {
                    ret[i] = -1.0;
                }
            }
        }

        return ret;
    }
}
