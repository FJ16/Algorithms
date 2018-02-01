public class UglyNumber {
    public int nthUglyNumberII(int n) {
        if (n == 0) return 0;
        // 第n-th问题， 可以想到尝试用 heap 解决, 这题类似heap但其实不完全相同！！！
        // 通过观察，ugly number 只能从给定的 prime factors set了取组合，因此例如，有选2，那么可能的ugly number formed from 2 are: 1x2, 2x2, 3x2...
        // 所以我们就有了 n 个 子列表，n = set length :
        // 1x2, 2x2, 3x2, 4x2, 5x2, 6x2, 8x2...(no 7 here!)
        // 1x3, 2x3, 3x3, 4x3, 5x3, 6x3, 8x3...
        // 1x5, 2x5, 3x5, 4x5, 5x5, 6x5, 8x5...
        // 只要每次取一个 子列表 头上 最小的 加入Result即可，类似 merge k sorted lists!!!!!
        // !!!但是注意！！这里子列表的乘数不是加一递增的，因为该乘数也必须是 之前出现在ugly number中的数！！
        int[] ugly = new int[n];
        ugly[0] = 1;
        int[] idx = new int[]{0, 0, 0};
        for (int i = 1; i < n; i++) {
            int p2 = ugly[idx[0]] * 2; int p3 = ugly[idx[1]] * 3; int p5 = ugly[idx[2]] * 5;
            int min = Math.min(p2, Math.min(p3, p5));
            ugly[i] = min;
            if (min == p2) idx[0]++;
            if (min == p3) idx[1]++;
            if (min == p5) idx[2]++;
        }
        return ugly[n - 1];
    }

    public int UglyNumber(int n) {
        return n;
    }
}
