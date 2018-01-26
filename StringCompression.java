public class StringCompression {
    public String getRes(String input) {
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i == 0 || input.charAt(i) != input.charAt(i - 1)) {
                if (count > 1) res.append(count + "");
                count = 0;
                res.append(input.charAt(i));
            }
            count++;
        }

        // post processing to append the final character count
        if (count > 1) res.append(count + "");
        return res.toString();
    }
}
