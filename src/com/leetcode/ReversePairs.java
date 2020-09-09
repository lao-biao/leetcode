package com.leetcode;

/**
 * 面试题#51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 * <p>
 * 输入: [7,5,6,4]
 * 输出: 5
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class ReversePairs {

    public static void main(String[] args) {

    }

    /**
     * 暴力算法：超时
     *
     * @param nums
     * @return
     */
    public int reversePairsBF(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j])
                    res++;
            }
        }
        return res;
    }

    /**
     * 借助归并排序：分治法
     * 时间复杂度：O(n log n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length - 1];   //临时数组，用于存放有序数组
        return merge(nums, 0, nums.length - 1, temp);
    }

    /**
     * 归并排序
     * 采用递归
     *
     * @param nums
     * @param low
     * @param high
     * @param temp  避免在递归中重复创建，减少内存消耗
     * @return
     */
    private int merge(int[] nums, int low, int high, int[] temp) {
        if (low >= high)    //递归结束条件
            return 0;
        int mid = low + (high - low) / 2;
        int res = merge(nums, low, mid, temp) + merge(nums, mid + 1, high, temp);

        int i = low, j = mid + 1, k = 0;
        while (i <= mid && j <= high) {
            res += nums[i] <= nums[j] ? j - (mid + 1) : 0;
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            res += j - (mid + 1);
            temp[k++] = nums[i++];
        }
        while (j <= high)
            temp[k++] = nums[j++];
        System.arraycopy(temp, 0, nums, low, high - low + 1);   //复制到原数组中
        return res;
    }

    /**
     * 归并排序
     * 求逆序对数
     * <p>
     * 如　设有数列{6，202，100，301，38，8，1}
     * 初始状态：6,202,100,301,38,8,1
     * 第一次归并后：{6,202},{100,301},{8,38},{1}，比较次数：3；
     * 第二次归并后：{6,100,202,301}，{1,8,38}，比较次数：4；
     * 第三次归并后：{1,6,8,38,100,202,301},比较次数：4；
     * 总的比较次数为：3+4+4=11；
     * 逆序数为14；
     * <p>
     * https://baike.baidu.com/item/%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int[] mergeSort(int[] nums, int low, int high) {
        if (low == high)
            return new int[]{nums[low]};

        int mid = low + (high - low) / 2;
        int[] leftArr = mergeSort(nums, low, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, high); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }
}
