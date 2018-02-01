import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianInDataStream {

    class MedianFinder {

        /** initialize your data structure here. */
        PriorityQueue<Integer> smaller;
        PriorityQueue<Integer> larger;

        public MedianFinder() {
            // max-heap
            smaller = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
                @Override
                public int compare(Integer a, Integer b) {
                    if (a == b) return 0;
                    return a < b ? 1 : -1;
                }
            });

            // natural min heap
            larger = new PriorityQueue<Integer>();
        }

        public void addNum(int num) {
            int ss = smaller.size();
            int ls = larger.size();
            if (ss == 0 && ls == 0) smaller.offer(num);
            else if (num <= smaller.peek()) {
                if (ss - ls >= 1) {
                    larger.offer(smaller.poll());
                }
                smaller.offer(num);
            }
            else if (ls > 0 && num < larger.peek()) {
                if (ss <= ls) smaller.offer(num);
                else larger.offer(num);
            }
            else {
                if (ls - ss >= 1) {
                    smaller.offer(larger.poll());
                }
                larger.offer(num);
            }
        }

        public double findMedian() {
            // corner case 都空throw?

            if (smaller.size() == larger.size()) return (double) smaller.peek() + ((double) larger.peek() - (double) smaller.peek()) / 2;
            return smaller.size() > larger.size() ? smaller.peek() : larger.peek();
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
