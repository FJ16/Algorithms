public class SquareRoot {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;

        int low = 0;
        int high = x;
        int ans = x;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (mid == x / mid) return mid;
            else if (mid < x / mid) {
                low = mid + 1;
                ans = mid;
            }
            else high = mid - 1;
        }

        return ans;
    }

    public double mySqrtDouble(double x, Double precise) {
        double low = 0;
        double high = x;
        double prec = precise != null ? precise : 0.0001;

        if (x < 0) {
            throw new RuntimeException("Negetive number cannot have a sqrt root.");
        }

        while (high - low > prec) {
            double mid = low + (high - low) / 2;

            if (mid < x / mid) {
                low = mid;
            }
            else high = mid;
        }

        return (low + high) / 2;
    }
}
