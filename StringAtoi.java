public class StringAtoi {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;

        int p = 0;
        while (p < str.length() && str.charAt(p) == ' ') p++; // remember to check the boundary of the string when moving pointer

        boolean isNeg = false;
        if (p < str.length() && !isNum(str.charAt(p))) {
            if (str.charAt(p) == '-') {
                isNeg = true;
                p++;
            }
            else if (str.charAt(p) == '+') {
                p++;
            }
            else return 0;
        }

        int res = 0;
        for (int i = p; i < str.length(); i++) {
            if (isNum(str.charAt(i))) {
                int next = res * 10 + Character.getNumericValue(str.charAt(i));

                if (next / 10 != res) { // 判断进位后是否溢出，只需要让进位后的数/10即可，结果自动舍去刚加上的一位
                    return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                    // 如果不相等，则说明溢出
                    // 能除回来则说明没有溢出
                }else {
                    res = next;
                }
            }
            else break; // clarify 是否遇到非 number 就立刻停止
        }
        return isNeg ? -1 * res : res;
    }

    private boolean isNum(Character c) {
        return c >= '0' && c <= '9';
    }
}
