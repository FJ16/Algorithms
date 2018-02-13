public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int res = 0;
        int[] minMax = new int[height.length];

        int leftMax = 0;
        for (int i = 0; i < minMax.length; i++) {
            minMax[i] = leftMax;
            leftMax = Math.max(leftMax, minMax[i]);
        }

        int rightMax = 0;
        for (int i = minMax.length - 1; i >= 0; i--) {
            int curMin = Math.min(rightMax, minMax[i]);
            if (height[i] < curMin) {
                res += curMin - height[i];
            }
            rightMax = Math.max(rightMax, height[i]);
        }

        return res;
    }
}
