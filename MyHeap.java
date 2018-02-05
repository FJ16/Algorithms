import java.util.NoSuchElementException;

public class MyHeap {
    // how to implement it thread safe ？

    // heap : complete binary tree -> parent node val > or < child nodes val

    // Since the Heap is guaranteed a complete binary tree, then we must have index - TreeNode mapping relation:
    /*
        Index of cur = i, what is the index of the two child nodes? and its parent node?
        left child of index i = 2 * i + 1;
        right child of index i = 2 * i + 2;
        parent node of index i = (i - 1) / 2;
     */

    // fields
    private int[] container;
    private int size; // current size, dynamically change
    // methods

    public MyHeap(int initializedCap) {
        if (initializedCap <= 0) {
            throw new IllegalArgumentException("Capacity can not be <= 0");
        }
        container = new int[initializedCap];
        size = 0;
    }

    public MyHeap(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array can not be null or empty");
        }
        container = array;
        size = array.length;
        heapify();
    }

    public void offer(int x) {
        // [0, size)
        if (size == container.length) {
            int[] newContainer = new int[(int)(size * 1.5)];
            for (int i = 0; i < container.length; i++) {
                newContainer[i] = container[i];
            }
            container = newContainer;
        }
        container[size] = x;
        percolatedUp(size);
        size++;
    }

    public int poll() throws Exception {
        if (size == 0) throw new NoSuchElementException("heap is empty");

        int re = container[0];
        swap(container, 0, size - 1);
        size--;
        //container[size] = 0; ?
        percolatedDown(0);
        return re;
    }

    public int peek() throws Exception {
        if (size == 0) throw new NoSuchElementException("heap is empty");
        return container[0];
    }

    public int update(int index, int val) { // need return last value where is updated
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("index out of heap's bounds");
        }
        int lastVal = container[index]; // cache出先前同位置的值，以判定新值 是 percolated up 还是 percolated down
        container[index] = val;
        if (lastVal > val) {
            percolatedUp(index);
        } else if (lastVal < val){
            percolatedDown(index);
        }
        return lastVal;
    }

    private void percolatedUp(int cur) {
        int parent = (cur - 1) / 2;

        while (parent >= 0 && container[parent] > container[cur]) {
            swap(container, parent, cur);
            cur = parent;
            parent = (cur - 1) / 2;
        }
    }

    private void percolatedDown(int cur) {
        while (cur <= (size - 2) / 2) {
            //last non-leaf node --> 由最末尾node index根据parent node mapping 公式得来：((size - 1) - 1) / 2;
            int right = cur * 2 + 2;
            int left = cur * 2 + 1;

            int swapCandidate = left; // complete binary tree, always fill left first, unless the right node is smaller
            if (right < size && container[right] < container[left]) {
                swapCandidate = right;
            }

            if (container[cur] > container[swapCandidate]) {
                swap(container, cur, swapCandidate);
            } else {
                break;
            }

            cur = swapCandidate;
        }
    }

    private void heapify() {
        int cur = (size - 1) - 1 / 2; // 从最后一个 非leaf node 开始 percolate down
        while (cur >= 0) {
            percolatedDown(cur);
            cur--; // 存在数组里，减减 就可以找到下一个
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
