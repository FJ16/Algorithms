public class ReverseSentence {
    public String reverseWords(String s) {
        String slim = trim(s); // implement trim() by own, 我们也可以在自己写的trim里去掉类似 a__b 到 a_b
        // 但如果要用built-in的trim(), 那么只会去 leading zero & tailing zero
        char[] str = slim.toCharArray();
        int i = 0;
        for (; i < str.length; i++) {
            int j = i;
            while (j < str.length && str[j] != ' ') j++;
            reverse(i, j - 1, str);
            // if (j == str.length - 1) reverse(i, j, str);
            i = j;
        }
        reverse(0, str.length - 1, str);

        // StringBuilder res = new StringBuilder();
        // for (int j = 0; j < str.length; j++) {
        //     if (j > 0 && str[j] == ' ' && str[j - 1] == ' ')
        //         continue;
        //     res.append(str[j]);
        // }

        return new String(str);
    }

    private String trim(String str) {
        if (str == null || str.length() == 0) return str;

        char[] tmp = str.toCharArray(); // 可以用StringBuilder out-place做，也可以用char[] in-place slow/fast 做
        int slow = 0;
        int fast = 0;

        while (fast < tmp.length) {
            // leading space
            if (tmp[fast] == ' ' && (fast == 0 || tmp[fast] == tmp[fast - 1])) {
                fast++;
                continue;
            }
            tmp[slow++] = tmp[fast++];
        }
        if (slow == 0) return "";
        return tmp[slow - 1] == ' ' ? new String(tmp, 0, slow - 1) : new String(tmp, 0, slow);
    }

    private void reverse(int s, int e, char[] str) {
        while (s < e) {
            char temp = str[s];
            str[s] = str[e];
            str[e] = temp;
            s++;
            e--;
        }
        return;
    }
}
