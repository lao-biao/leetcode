package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    class Solution {
        /**
         * 暴力算法
         * 枚举比较，从第一个数到最后一个数逐个求和比较，直至匹配成功
         * <p>
         * 时间复杂度：T(n)=O(n^2)    n-1+n-2+...+1=(n-1)n/2
         * 空间复杂度：T(n)=O(1)
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSumByBF(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[]{-1, -1};
            }
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (target == nums[i] + nums[j]) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{-1, -1};
        }

        /**
         * 两遍hash表
         * 第一遍：将数组转换成hash表
         * 第二遍：在hash表中查找
         * 时间复杂度：T(n)=O(n)
         * 空间复杂度：T(n)=O(n)
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSumByHashMap(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[]{-1, -1};
            }
            Map<Integer, Integer> table = new HashMap<>();
            //将数组转换成hash表
            for (int i = 0; i < nums.length; i++) {
                table.put(nums[i], i);
            }
            //查找
            for (int i = 0; i < nums.length - 1; i++) {
                int searched = target - nums[i];
                if (table.containsKey(searched) && table.get(searched) != i) {
                    return new int[]{i, table.get(searched)};
                }
            }
            return new int[]{-1, -1};
        }

        /**
         * 一遍hash表，边插入边查找
         * 时间复杂度：T(n)=O(n)
         * 空间复杂度：T(n)=O(n)
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSumByHashMapOnce(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[]{-1, -1};
            }
            Map<Integer, Integer> table = new HashMap<>();
            //将数组转换成hash表
            for (int i = 0; i < nums.length; i++) {
                int searched = target - nums[i];
                //查找
                if (table.containsKey(searched) && table.get(searched) != i) {
                    return new int[]{table.get(searched), i};
                }
                table.put(nums[i], i);
            }
            return new int[]{-1, -1};
        }
    }
}
