package com.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * 23. 合并K个排序链表
 * <p>
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class MergeKLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        ListNode res = lists[0];   //链表头
        for (int i = 1; i < lists.length; i++) {
            res.next = mergeTwoLists(res, lists[i]);
        }
        return res;
    }

    /**
     * 顺序比较
     *
     * @param one
     * @param another
     * @return
     */
    private ListNode mergeTwoLists(ListNode one, ListNode another) {
        if (one == null || another == null) {
            return one != null ? one : another;
        }
        ListNode head = new ListNode(-1);   //虚拟的头
        ListNode tail = head;
        while (one != null && another != null) {
            if (one.val < another.val) {
                tail.next = another;
                one = one.next;
            } else {
                tail.next = another;
                another = another.next;
            }
            tail = tail.next;
        }
        tail.next = (one != null ? one : another);
        return head.next;
    }

    /**
     * 暴力转换：链表变成数组，数组排序，转换回链表
     */
    public ListNode mergeKListsByArray(ListNode[] lists) {
        int num = 0;
        for (ListNode listNode : lists)
            while (listNode != null) {
                num++;
                listNode = listNode.next;
            }
        if (num == 0)  //链表为空
            return null;
        //创建数组
        int[] res = new int[num];
        int len = 0;
        //元素装填
        for (ListNode listNode : lists)
            while (listNode != null) {
                res[len] = listNode.val;
                len++;
                listNode = listNode.next;
            }
        //排序
        Arrays.sort(res);
        //转换回链表
        ListNode result = new ListNode(res[0]);
        ListNode head = result;
        for (int i = 1; i < res.length; i++) {
            head.next = new ListNode(res[i]);
            head = head.next;
        }
        return result;
    }
}
