package com.leetcode;

import java.util.HashMap;

/**
 * 面试题 01.04. 回文排列
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * <p>
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * <p>
 * 回文串不一定是字典当中的单词。
 * <p>
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci/
 */
public class CanPermutePalindrome {
    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("tactcoa"));
    }

    public static boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray(); //串转化成数组
        for (char temp : chars) {
            if (map.containsKey(temp))  //判断是否包含当前字符
                map.remove(temp);   //移除
            else
                map.put(temp, 1);   //添加字符到hashMap中
        }
        return map.size() == 0 || map.size() == 1;
    }
}
