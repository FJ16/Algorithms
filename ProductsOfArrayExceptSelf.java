public class ProductsOfArrayExceptSelf {
    public int[] products(int[] array) {
        if (array == null || array.length == 0) return null;
        // 错位相乘，prefix product不带上自己，第二遍倒着走的时候再乘上
        int[] res = new int[array.length];
        res[0] = 1; // 错位用
        for (int i = 1; i < array.length; i++) {
            res[i] = res[i - 1] * array[i - 1]; // res[i - 1]上一步的prefix product
        }

        int missing = 1;
        for (int i = array.length - 1; i >= 0; i--) {
            res[i] *= missing;
            missing *= array[i];
        }

        return res;
    }

}
