package com.leetcode;

import java.util.Arrays;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n4 = new ListNode(4);
        ListNode nn1 = new ListNode(1);
        ListNode nn3 = new ListNode(3);
        ListNode nn4 = new ListNode(4);

        ListNode l1 = n1;
        l1.next = n2;
        l1.next.next = n4;

        ListNode l2 = nn1;
        l2.next = nn3;
        l2.next.next = nn4;

        System.out.println(l1.toString());
        System.out.println(l2.toString());
//        ListNode listNode = mergeTwoLists(l1, l2);
//        System.out.println(listNode.toString());
        ListNode listsBF = mergeTwoListsBF(l1, l2);
        System.out.println(listsBF.toString());


    }

    /**
     * 迭代法
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode temp = res;    //操作结点
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    temp.next = l2;
                    l2 = l2.next;
                } else {
                    temp.next = l1;
                    l1 = l1.next;
                }
            } else if (l1 == null && l2 != null) {  //l1为空
                temp.next = l2;
                l2 = l2.next;
            } else if (l2 == null && l1 != null) {  //l2为空
                temp.next = l1;
                l1 = l1.next;
            }
            temp = temp.next;
        }
        return res.next;
    }

    /**
     * 暴力转换
     */
    public static ListNode mergeTwoListsBF(ListNode l1, ListNode l2) {
        //长度num
        ListNode temp = l1;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        temp = l2;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        //判空
        if (len==0)
            return null;
        int[] arr = new int[len];
        int index = 0;
        //填入数组
        while (l1 != null) {
            arr[index] = l1.val;
            index++;
            l1 = l1.next;
        }
        while (l2 != null) {
            arr[index] = l2.val;
            index++;
            l2 = l2.next;
        }
        //排序
        Arrays.sort(arr);
        //转换
        ListNode res = new ListNode(arr[0]);
        temp = res;
        for (int i = 1; i < len; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
        return res;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}