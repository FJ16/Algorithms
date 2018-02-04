public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;

        // for for loop 一对一对check, 也可以用 sort 然后 max heap做？expand & generate - BFS2
        int res = 0;
        // if the word only include limited number of characters, such as lower cases, we can use bits map: 1 int -> 1 str
        int[] bitsMap = getBitsMap(words);

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; i < words.length; j++) {
                if ((bitsMap[i] & bitsMap[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }

        return res;
    }

    private int[] getBitsMap(String[] words) {
        int[] res = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            int bitsMap = 0;
            for (int j = 0; j < words[i].length(); j++) {
                bitsMap |= 1 << (words[i].charAt(j) - 'a');
            }
            res[i] = bitsMap;
        }

        return res;
    }
}
