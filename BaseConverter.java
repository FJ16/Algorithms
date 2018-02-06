public class BaseConverter {
    //任意进制间转换， idea: 由十进制作为中介
    private final char[] numToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final String charToNum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String convert(String input, int m, int n) {
        if (input == null || input.length() == 0) throw new IllegalArgumentException("input can not be empty");

        long temp = getTenBaseNum(input, m);
        String result = getTargetBase(temp, n);
        return result;
    }

    private long getTenBaseNum(String input, int m) { //由低位到高位 按位权 展开 (位权 ：x^0, x^1 ...)
        char[] num = input.toCharArray();
        int len = num.length;

        long result = 0;
        if (m == 10) return Long.valueOf(input);

        long base = 1; // x^0
        for (int i = len - 1; i >= 0; i--) {

            int curNum = charToNum.indexOf(num[i]);
            result += base * curNum; // 当前位数 * 当前位的值
            base *= m; // x^0 * x -> x^1
        }
        return result;
    }

    private String getTargetBase(long tenBaseNum, int n) { // 除 target base, 取余，逆序排列 --> 商 == 0

        StringBuilder result = new StringBuilder();

        while (tenBaseNum != 0) {
            int remain = (int)tenBaseNum % n;
            result.append(numToChar[remain]);

            tenBaseNum /= n;
        }

        return result.reverse().toString();
    }
}
