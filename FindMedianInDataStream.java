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
            else if (num <= smaller.peek()) { // for sure, we gonna try to offer the current num into smaller heap contianer
                if (ss - ls >= 1) {
                    larger.offer(smaller.poll());
                    // check size difference to make sure the balance, balance will ensure the middle position
                }
                smaller.offer(num);
            }
            else if (ls > 0 && num < larger.peek()) { // if the current num is between Max-in-smaller and Min-in-larger
                if (ss <= ls) smaller.offer(num);    // then we should only consider the size balance, since for the value only
                else larger.offer(num);             // will be ok to go both of those heaps
            }
            else {  // otherwise the offer the number whose value is larger than Min-in-larger to the larger heap.
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