public class FindNumInRotatedArray {
    // LC33
    public int findWithNoDuplicate(int[] input, int target) {
        if (input == null || input.length == 0) return -1;

        int left = 0;
        int right = input.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (input[mid] == target) return mid;

            if (input[mid] >= input[left]) {
                // 这里总归要处理 等于情况，因为 left + right 最后一定会走到 相邻两个数，必然一奇一偶，那么 除2 必然 "靠左"，
                // 所以这里 等于号 我们给左边去处理，否则可能 mid 反复被计算出 == left的值
                if (target <= input[mid] && target >= input[left]) {
                    right = mid;
                }
                else left = mid + 1;
            } else {
                if (target <= input[right] && target >= input[mid]) {
                    left = mid;
                }
                else right = mid - 1;
            }

        }

        return -1;
    }

    public boolean findWithDuplicate(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                //any of the two sides won't change the result but can help remove duplicate from
                //consideration, here we just use end-- but left++ works too
            } else {
                end--;
            }
        }

        return false;
    }
}
