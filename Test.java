import java.util.*;


public class Test {
    public static void main(String[] args) {
//        // ebay 1: String compression
//        StringCompression ebay1 = new StringCompression();
//        String res1 = ebay1.getRes("abbbbbccdddefff");
//        System.out.println(res1);
//
//        // ebay 2: Subsets
//        Subset ebay2 = new Subset();
//        List<String> res2 = ebay2.allSubsets("abed");
//        printStrList(res2);
//
//        // ebay 3: Consecutive Array find duplicate
//        ConsecutiveFindDup ebay3 = new ConsecutiveFindDup();
//        int res3 = ebay3.findDup(new int[]{1,2,3,4,5,2,6,7,8});
//        System.out.println(res3);
//
//        // ebay 4: Maximum num in K length sub-array;
//        SlidingWindowMaximum ebay4 = new SlidingWindowMaximum();
//        int[] res4 = ebay4.getRes(new int[]{1,3,-1,-3,5,3,6,7}, 3);
//        printIntArray(res4);
//
//        // ebay 5: merge sort
//        MergeSort ebay5 = new MergeSort();
//        int[] res5 = ebay5.mergeSort(new int[]{2,3,5,7,6,8,3});
//        printIntArray(res5);
//
//        System.out.println(new String(new char[]{'a','b'}, 0, 2));
//
//        SquareRoot sqrtr = new SquareRoot();
//        System.out.println(sqrtr.mySqrtDouble(192.332, null));
//
//        BaseConverter bc = new BaseConverter();
//        String res = bc.convert("2112321", 4, 16);
//        System.out.println(res)；

        System.out.println(0%3);

        Kdistinct t = new Kdistinct();
        printStrList(t.find("awaglknagawunagwkwagl", 4));
    }

    private static void printIntList(List<Integer> list) {
        for (Integer n : list) System.out.println(n);
    }

    private static void printStrList(List<String> list) {
        for (String n : list) System.out.println(n);
    }

    private static void printIntArray(int[] res) {
        for (int n : res) System.out.println(n);
    }
}

