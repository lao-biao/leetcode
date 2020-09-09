package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problem/longest-substring-without-repeating-characters/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubString {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstringQueue("abcabcbb"));

    }

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] string = s.toCharArray();
        Set<Character> res = new HashSet<>();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        // 枚举左指针的位置，初始值隐性地表示为 -1
        for (int i = 0; i < string.length; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                res.remove(string[i - 1]);
            }
            while (rk + 1 < string.length && !res.contains(string[rk + 1])) {
                // 不断地移动右指针
                res.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 队列
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringQueue(String s) {
        if (s.length() == 0)
            return 0;

        Queue<Character> res = new LinkedList<>();
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            if (res.contains(s.charAt(i))) {
                max = Math.max(res.size(), max);
                Character remove = res.remove();    //队首元素出队
                while (remove!=s.charAt(i))     //判断当前元素是否是出队元素
                    remove = res.remove();
            }
            res.add(s.charAt(i));
        }
        return Math.max(max, res.size());
    }

}
