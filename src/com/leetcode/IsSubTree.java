package com.leetcode;

/**
 * 572. 另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * <p>
 * 示例 1:
 * 给定的树 s:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 t：
 * <p>
 * 4
 * / \
 * 1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 2:
 * 给定的树 s：
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * /
 * 0
 * 给定的树 t：
 * <p>
 * 4
 * / \
 * 1   2
 * 返回 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problem/subtree-of-another-tree/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSubTree {
    public static void main(String[] args) {

    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    /**
     * 深度优先排序
     *
     * @param root
     * @param sub
     * @return
     */
    private boolean dfs(TreeNode root, TreeNode sub) {
        if (root == null) {
            return false;
        }
        return check(root, sub) || dfs(root.left, sub) || dfs(root.right, sub);
    }

    private boolean check(TreeNode root, TreeNode sub) {
        if (root == null && sub == null)
            return true;
        if ((root != null && sub == null) || (root == null && sub != null) || (root.val != sub.val))
            return false;
        return check(root.left, sub.left) && check(root.right, sub.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}