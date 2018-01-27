public class ConsecutiveFindDup {
    //e.g. [1,2,3,4,4,5,6,7] --> return 4;  Binary Search
    // 如果在consecutive序列里，插入duplicate元素，那么其插入位置后面的坐标对应关系会发生变化，因此我们要找第一个引起变化的位置
    public int findDup(int[] input) {
        if (input == null || input.length == 0) return -1;

        int left = 0;
        int right = input.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (input[mid] == mid + 1) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return input[left];
    }
}
