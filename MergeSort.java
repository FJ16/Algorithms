public class MergeSort {
    public int[] mergeSort(int[] input) {
        if (input == null || input.length == 0) return input;
        sort(input, new int[input.length], 0, input.length - 1);
        // 用 多个 pointers 时，一定要考虑他们的物理意义，以及 开闭规则，因为我们递归的时候要保持同一个规则
        // 这里 right 我们选择 length - 1, which is 我们可以选到的， 在数组中可以选到的index.
        return input;
    }

    private void sort(int[] input, int[] helper, int left, int right) {
        if (left >= right) return;
        // binary break down the current array into 2 parts
        int mid = left + (right - left) / 2;

        sort(input, helper, left, mid); // merge 分 mid的时候 关键的问题：选择将mid放在哪半段？ here we chose left part
        sort(input, helper, mid + 1, right);

        merge(input, helper, left, mid, right);
    }

    private void merge(int[] input, int[] helper, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = input[i];
        }

        int leftMost = left;
        int rightMost = mid + 1;
        while (leftMost <= mid && rightMost <= right) {
            if (helper[leftMost] <= helper[rightMost]) { // 谁小移谁，有小到大排
                input[left++] = helper[leftMost++];
            } else {
                input[left++] = helper[rightMost++];
            }
        }

        // here are two corner case:
        // case 1: 前半段总体值都小，后半段没有merge完，但是因为他们已经放好了，所以不用管
        // case 2: 后半段总体值较大，前半段没有merge完，那么要做post-process, 将没有正确merge完的element merge进去
        while (leftMost <= mid) {
            input[left++] = helper[leftMost++];
        }
    }
}
