import java.util.*;


public class Test {
    public static void main(String[] args) {
        // ebay 1: String compression
        StringCompression ebay1 = new StringCompression();
        String res1 = ebay1.getRes("abbbbbccdddefff");
        System.out.println(res1);

        // ebay 2: Subsets
        Subset ebay2 = new Subset();
        List<String> res2 = ebay2.allSubsets("abed");
        printStrList(res2);

        // ebay 3: Consecutive Array find duplicate
        ConsecutiveFindDup ebay3 = new ConsecutiveFindDup();
        int res3 = ebay3.findDup(new int[]{1,2,3,4,5,2,6,7,8});
        System.out.println(res3);

    }

    private static void printIntList(List<Integer> list) {
        for (Integer n : list) System.out.println(n);
    }

    private static void printStrList(List<String> list) {
        for (String n : list) System.out.println(n);
    }
}

