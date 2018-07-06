public class FirstUniqueChar {
    public int firstUniqChar(String s) {
        int[] bitMap = new int[16];
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int row = c / 16;
            int col = c % 16;
            int mask = 3;
            int status = (bitMap[row] >>> col * 2) & 3;
            if (status == 0) {
                bitMap[row] |= (1 << col * 2);
            }
            else if (status == 1) {
                bitMap[row] |= (2 << col * 2);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int row = c / 16;
            int col = c % 16;
            int status = (bitMap[row] >>> col * 2) & 3;
            if (status == 1) {
                return i;
            }
        }
        return -1;
    }
}
