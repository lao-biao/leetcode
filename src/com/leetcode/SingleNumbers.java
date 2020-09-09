package com.leetcode;

import java.util.Arrays;

/**
 * 面试题56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *  
 * <p>
 * 限制：
 * <p>
 * 2 <= nums <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 */
public class SingleNumbers {
    public static void main(String[] args) {
        int[] res = singleNumbers(new int[]{4, 1, 4, 6});
//        System.out.println(Arrays.toString(res));
        int number = -7;
        String binaryNum = Integer.toBinaryString(number);
        System.out.println(number + " toBinary :" + binaryNum);
        System.out.println("" + (1 & -1));
    }

    /**
     * 分组位运算
     * 由于数组中存在着两个数字不重复的情况，我们将所有的数字异或操作起来，最终得到的结果是这两个数字的异或结果
     * 按奇偶分组
     * 4 :0b100
     * 0 ^ 4 = 0b100
     * 1 :0b01
     * 1 ^ 0b100 = 0b101
     * 6 :0b110
     * 0b101 ^ 6 = 0b011
     * 0b011 ^ 4 = 0b111
     * 最低位的1: div=1
     * 还原
     * 4 & 1 = 0  b = 0 ^ 4 = 0b100 = 4
     * 1 & 1 = 1  a = 1 ^ 1 = 0b1
     * 4 & 1 = 0  b = 4 ^ 4 = 0b0
     * 6 & 1 = 0  b = 0 ^ 6 = 0b110 = 6
     *
     * @param nums
     * @return
     */
    public static int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums)
            ret ^= n;
        int div = 1;
        //获取ret最低位的1
        while ((div & ret) == 0)    //或 int div=ret&(-ret);
            div <<= 1;
        int a = 0, b = 0;   //记录两个单次出现的数的位置
        //按奇偶分组
        for (int n : nums)
            if ((div & n) != 0) //记录奇数
                a ^= n;
            else    //记录偶数
                b ^= n;
        return new int[]{a, b};
    }
}
