package com.leetcode;

/**
 * 1095. 山脉数组中查找目标值
 * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
 * <p>
 * 如果不存在这样的下标 index，就请返回 -1。
 * <p>
 *  
 * <p>
 * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
 * <p>
 * 首先，A.length >= 3
 * <p>
 * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
 * <p>
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *  
 * <p>
 * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
 * <p>
 * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 * MountainArray.length() - 会返回该数组的长度
 *  
 * <p>
 * 注意：
 * <p>
 * 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
 * <p>
 * 为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：array = [1,2,3,4,5,3,1], target = 3
 * 输出：2
 * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 * 示例 2：
 * <p>
 * 输入：array = [0,1,2,4,2,1], target = 3
 * 输出：-1
 * 解释：3 在数组中没有出现，返回 -1。
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-in-mountain-array
 */
public class FindInMountainArray {
    public static void main(String[] args) {

    }

    /**
     * 三次二分法
     *
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        //二分法查找峰值
        int len = mountainArr.length();
        int top = findMountainTop(mountainArr, 0, len - 1);
        int index = findFromMountainLeft(mountainArr, 0, top, target);
        if (index == -1)
            return findFromMountainRight(mountainArr, top, len - 1, target);

        return index;
    }

    private int findFromMountainRight(MountainArray mountainArr, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) > target) {
                // 如果当前的数比目标值小，则目标数在小的一部分(右边)
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (mountainArr.get(left) == target)
            return left;
        return -1;
    }

    private int findFromMountainLeft(MountainArray mountainArr, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < target) {
                // 如果当前的数比目标值小，则目标数在大的一部分
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //剩余一个数时，判断是否是目标值
        if (mountainArr.get(left) == target)
            return left;

        return -1;
    }

    private int findMountainTop(MountainArray mountainArr, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                // 如果当前的数比右边的数小，它一定不是山顶
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

interface MountainArray {
    public int get(int index);

    public int length();
}

class MountainArrayImpl implements MountainArray {
    private int[] arr;
    private int size;

    public MountainArrayImpl(int[] arr) {
        this.arr = arr;
        this.size = this.arr.length;
    }

    @Override
    public int get(int index) {
        return this.arr[index];
    }

    @Override
    public int length() {
        return this.size;
    }

}
