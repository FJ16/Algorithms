import java.util.PriorityQueue;

public class KthLargestNumInArray {
    public int kthLargestNumberInArray(int[] arr, int k) {
        // n-size Max heap Or k-size Min-heap, Why k min heap？因为我们要保存 K个最大的，而第K大的是这里面 最小 的那个

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            minHeap.offer(arr[i]);
            if (minHeap.size() > k) { // 数量多余K个时候说明 top of heap 不属于K最大，可以poll掉
                minHeap.poll();
            }
        }

        // return minHeap.peek();
        return quickSelectWayToFindK(arr, 0, arr.length - 1, k - 1);
        // 数组里，第k个，index要 k - 1 ！！！
    }

    private int quickSelectWayToFindK(int[] arr, int start, int end, int k) {
        if (start >= end) return arr[start];
        int pivot = end; // pick a pivot by index of end, or we can do this randomly

        int left = start;
        int right = end - 1; // end 位置挖空，因为这是pivot位置
        while(left <= right) {
            if (arr[left] >= arr[pivot]) start++; // K largest here, so we leave the larger one in left
            else if (arr[right] < arr[pivot]) right--;
            else {
                swap(arr, left, right);
            }
        }
        // left 是我们最后找到的 放pivot的位置
        swap(arr, left, pivot);
        if (k == left) return arr[left];
        return left < k ? quickSelectWayToFindK(arr, left + 1, end, k) : quickSelectWayToFindK(arr, start, end - 1, k);
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // get random pivot
    private int randomPivot(int start, int end) {
        int range = end - start + 1;
        return (int)(Math.random()*range + start);
    }

}
