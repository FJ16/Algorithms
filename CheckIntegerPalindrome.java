public class CheckIntegerPalindrome {
    public boolean check(int input) {
        // 这题别二逼了，要搞清楚是 binary 格式 palindrome 还是Integer本身Palindrome - -
        // 此题是Integer Palindrome
        int temp = input;
        int rev = 0;

        while (temp > 0) { // temp > 0 表示可以一直取余
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }

        return rev == input;
        // Palindrome 两个特性：1. 两边往中间走一样，2. reverse过后也一样。
    }
}
