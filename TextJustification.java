import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int index = 0;
        while (index < words.length) {
            int remain = maxWidth;
            int count = 0;
            int start = index;
            while (index < words.length && remain - words[index].length() - 1 >= 0) {
                remain -= words[index].length() + 1;
                index++;
                count++;
            }

            StringBuilder line = new StringBuilder();
            if (index < words.length && remain < words[index].length()) {
                remain++;

                int add = count == 1 ? 0 : remain / (count - 1);
                int rem = count == 1 ? remain : remain % (count - 1);
                while (start < index - 1) {
                    line.append(words[start]);
                    line.append(' ');
                    for (int i = 0; i < add; i++) line.append(' ');
                    if (rem > 0) {
                        line.append(' ');
                        rem--;
                    }
                    start++;
                }
                line.append(words[start]);
                while (remain > 0) {
                    line.append(' ');
                    remain--;
                }
            } else {
                if (index < words.length && remain == words[index].length()) remain = 0;
                while (start < index) {
                    line.append(words[start]);
                    line.append(' ');
                    start++;
                }
                if (start < words.length && words[start].length() > 0) line.append(words[start]);
                while (remain > 0) {
                    line.append(' ');
                    remain--;
                }
                index++;
            }
            res.add(line.toString());
        }
        return res;
    }
}
