package com.leetcode;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class SearchInRotatedSortArray {
    public static void main(String[] args) {
//        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
//        System.out.println(search(new int[]{5, 1, 3}, 5));
//        System.out.println(search(new int[]{5, 1, 3}, 3));
        System.out.println(searchByRecursion(new int[]{5, 1, 3}, 3));
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int len = nums.length;
        if (len == 1)
            return nums[0] == target ? 0 : -1;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == nums[mid])    //找到
                return mid;

            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid])    //target在前半部分
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[len - 1])  //target在后半部分
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;  //没有找到
    }

    /**
     * 递归二分搜索
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchByRecursion(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        return search(nums, target, 0, nums.length - 1);
    }

    private static int search(int[] nums, int target, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left >= right && target != nums[mid])   //递归结束条件：没找到
            return -1;
        if (target == nums[mid])    //递归结束条件：找到
            return mid;
        if (nums[0] <= nums[mid]) { 
            if (nums[0] <= target && target < nums[mid])    //target在前半部分
                return search(nums, target, left, mid - 1);
            else
                return search(nums, target, mid + 1, right);
        } else {
            if (nums[mid] < target && target <= nums[nums.length - 1])  //target在后半部分
                return search(nums, target, mid + 1, right);
            else
                return search(nums, target, left, mid - 1);
        }
    }
}
