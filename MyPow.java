public class MyPow {
    public double getRes(int x, int n) {
        return helper(x, (long)n); // 这里 强制转换成long 型，是因为负数int下限 -2147483648 变成正数会溢出(2147483657 max)
    }

    // 注意这里n可以是负数，如果是负数那么 通过数学转换，x作为倒数 1/x运算，n变为正
    private double helper(int x, long n) {
        // base case
        if (n == 0) return 1; // x^0
        // n == 1不用单独处理，因为 x^1 会被break down to x^0 * x^0 * x;
        // 处理负数,根据数学原理变负为正
        if (n < 0) {
            x = 1 / x;
            n = -1 * n;
        }
        // recursion break down
        double half = helper(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }
}
