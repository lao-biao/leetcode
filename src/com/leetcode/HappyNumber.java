package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 示例：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 */
public class HappyNumber {
    public static void main(String[] args) {
        //测试
        System.out.println(isHappy(19));
//        System.out.println(isHappy(2));
    }

    /**
     * 集合法
     * @param n
     * @return
     */
    public static boolean isHappyBySet(int n) {
//        List<Integer> list = new ArrayList<>();
        //用set比list更快
        Set<Integer> list = new HashSet<>();
        while (n != 1 && !list.contains(n)) {
            list.add(n);
            /*
            char[] bits = Integer.toString(n).toCharArray();
            int num = 0;
            for (char bit : bits)
                num += (bit - '0') * (bit - '0');
            n = num;
             */
            int num = 0;
            while (n != 0) {
                int bit = n % 10;
                num += bit * bit;
                n = n / 10;
            }
            n = num;
        }
        return n == 1;
    }

    /**
     * 使用"快慢指针"思想找出循环："快指针"每次走两步，"慢指针"每次走一步，当二者相等时，即为一个循环周期。
     * 此时，判断是不是因为1引起的循环，是的话就是快乐数，否则不是快乐数。
     * <p>
     * 作者：linux-man
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/shi-yong-kuai-man-zhi-zhen-si-xiang-zhao-chu-xun-h/
     * 来源：力扣（LeetCode）
     */
    public static boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        } while (slow != fast);

        return slow == 1;
    }

    private static int bitSquareSum(int n) {
        int num = 0;
        while (n != 0) {
            int bit = n % 10;
            num += bit * bit;
            n = n / 10;
        }
        return num;
    }
}
