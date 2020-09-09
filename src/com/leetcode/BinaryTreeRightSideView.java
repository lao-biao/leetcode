package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * # 199二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 *   1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeRightSideView {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    /**
     * 二叉树按层次遍历
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);    //root入队
        while (!queue.isEmpty()) {
            int len = queue.size();
            while (--len >= 0) {
                TreeNode temp = queue.poll();   //出队
                if (temp != null) {
                    if (len == 0)
                        list.add(temp.val); //最右侧结点
                    if (temp.left != null)
                        queue.add(temp.left);
                    if (temp.right != null)
                        queue.add(temp.right);
                }
            }
        }
        return list;
    }

    /**
     * 先序遍历改造
     */
    public List<Integer> rightSideViewByPre(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    /**
     * 遍历顺序：根结点 右子树 左子树
     * @param root  根节点
     * @param level 当前层数
     * @param res   列表
     */
    private void dfs(TreeNode root, int level, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (level == res.size()) {
            res.add(root.val);
        }
        dfs(root.right, level + 1, res);    //先遍历右子树
        dfs(root.left, level + 1, res);
    }
}
