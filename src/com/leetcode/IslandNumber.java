package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 示例一：
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * <p>
 * 示例二：
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */
public class IslandNumber {

    /**
     * 解法：
     * 1.深度优先搜索
     * 2.广度优先搜索
     * 3.并查集
     */

    static class Solution {
        public static void main(String[] args) {
            char[][] grid = new char[][]{{'1', '1', '1', '1', '0'},
                    {'1', '1', '0', '1', '0'},
                    {'1', '1', '0', '0', '0'},
                    {'0', '0', '0', '0', '0'}};
            char[][] cloneForBFS = new char[grid.length][grid[0].length];
            char[][] cloneForUF = new char[grid.length][grid[0].length];
            //数组复制
            for (int i = 0; i < grid.length; i++) {
                System.arraycopy(grid[i], 0, cloneForBFS[i], 0, grid[i].length);
            }
            for (int i = 0; i < grid.length; i++) {
                System.arraycopy(grid[i], 0, cloneForUF[i], 0, grid[i].length);
            }
            System.out.println(numIslandsByDFS(grid));          //深度优先搜索
            System.out.println(numIslandsByBFS(cloneForBFS));   //广度优先搜索
            System.out.println(numIslandsByUF(cloneForUF));     //并查集
        }

        public static int numIslandsByDFS(char[][] grid) {
            if (grid == null || grid.length == 0) { //判空
                return 0;
            }
            int islandNum = 0;          //岛屿数量
            int row = grid.length;      //行数
            int col = grid[0].length;   //列数

            for (int r = 0; r < row; r++) {     //遍历列元素
                for (int c = 0; c < col; c++) { //遍历行元素
                    if (grid[r][c] == '1') {
                        islandNum++;        //岛屿数量+1
                        dfs(grid, r, c);    //循环深度优先搜索
                    }
                }
            }
            return islandNum;   //返回岛屿数量
        }

        /**
         * 深度优先搜索--借助递归
         * 遍历整个数组。
         * 如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。
         * 在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
         * 最终岛屿的数量就是我们进行深度优先搜索的次数
         *
         * @param grid
         * @param r
         * @param c
         */
        private static void dfs(char[][] grid, int r, int c) {
            int row = grid.length;      //行数
            int col = grid[0].length;   //列数

            if (r < 0 || c < 0 ||
                    r >= row || c >= col ||
                    grid[r][c] == '0') {    //递归结束条件
                return;
            }
            grid[r][c] = '0';     //重新标记
            dfs(grid, r - 1, c);    //正上方
            dfs(grid, r + 1, c);    //正下方
            dfs(grid, r, c - 1);    //正左方
            dfs(grid, r, c + 1);    //正右方
        }

        /**
         * 广度优先搜索--借助队列
         * 遍历整个数组。
         * 如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。
         * 在广度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
         * 直到队列为空，搜索结束。
         * 最终岛屿的数量就是我们进行广度优先搜索的次数。
         *
         * @param grid
         * @return
         */
        public static int numIslandsByBFS(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int islandsNum = 0;         //岛屿数量
            int row = grid.length;      //行数
            int col = grid[0].length;   //列数
            //遍历
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (grid[r][c] == '1') {    //岛屿
                        grid[r][c] = '0';   //重新标记
                        islandsNum++;
                        Deque<Integer> queue = new LinkedList<>();
                        queue.add(r * col + c);         //入队
                        while (!queue.isEmpty()) {      //队列不为空
                            int id = queue.remove();    //出队
                            //当前元素的位置
                            int cr = id / col;    //当前元素的行
                            int cc = id % col;    //当前元素的列
                            if (cr - 1 >= 0 && grid[cr - 1][cc] == '1') {   //正上方
                                queue.add((cr - 1) * col + cc);
                                grid[cr - 1][cc] = '0'; //重新标记
                            }
                            if (cr + 1 < row && grid[cr + 1][cc] == '1') { //正下方
                                queue.add((cr + 1) * col + cc);
                                grid[cr + 1][cc] = '0'; //重新标记
                            }
                            if (cc - 1 >= 0 && grid[cr][cc - 1] == '1') {   //正左方
                                queue.add(cr * col + cc - 1);
                                grid[cr][cc - 1] = '0'; //重新标记
                            }
                            if (cc + 1 < col && grid[cr][cc + 1] == '1') { //正右方
                                queue.add(cr * col + cc + 1);
                                grid[cr][cc + 1] = '0'; //重新标记
                            }
                        }
                    }
                }
            }
            return islandsNum;
        }

        /**
         * 并查集
         * 遍历整个数组。
         * 如果一个位置为 1，则将其与相邻四个方向上的 1 在并查集中进行合并。
         * 最终岛屿的数量就是并查集中连通分量的数目。
         * 时间复杂度：O(MN * \alpha(MN))O(MN∗α(MN))，其中 M 和 N 分别为行数和列数。注意当使用路径压缩（见 find 函数）和按秩合并（见数组 rank）实现并查集时，单次操作的时间复杂度为 \alpha(MN)α(MN)，其中 \alpha(x)α(x) 为反阿克曼函数，当自变量 xx 的值在人类可观测的范围内（宇宙中粒子的数量）时，函数 \alpha(x)α(x) 的值不会超过 55，因此也可以看成是常数时间复杂度。
         */
        static class UnionFind {
            int count;
            int[] parent;   //陆地位置集合
            int[] rank;     //秩

            public UnionFind(char[][] grid) {   //构造函数
                count = 0;
                int m = grid.length;
                int n = grid[0].length;
                parent = new int[m * n];
                rank = new int[m * n];
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (grid[i][j] == '1') {
                            parent[i * n + j] = i * n + j;
                            ++count;
                        }
                        rank[i * n + j] = 0;
                    }
                }
            }

            public int find(int i) {    //递归查找
                if (parent[i] != i)
                    parent[i] = find(parent[i]);
                return parent[i];
            }

            public void union(int x, int y) {   //连接
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    if (rank[rootX] > rank[rootY])
                        parent[rootY] = rootX;
                    else if (rank[rootX] < rank[rootY])
                        parent[rootX] = rootY;
                    else {
                        parent[rootY] = rootX;
                        rank[rootX] += 1;
                    }
                    --count;
                }
            }

            public int getCount() { //获取数量
                return count;
            }
        }

        public static int numIslandsByUF(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;

            UnionFind uf = new UnionFind(grid);
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        grid[r][c] = '0';
                        if (r - 1 >= 0 && grid[r - 1][c] == '1') //上
                            uf.union(r * nc + c, (r - 1) * nc + c);
                        if (r + 1 < nr && grid[r + 1][c] == '1') //下
                            uf.union(r * nc + c, (r + 1) * nc + c);
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') //左
                            uf.union(r * nc + c, r * nc + c - 1);
                        if (c + 1 < nc && grid[r][c + 1] == '1') //右
                            uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
            return uf.getCount();   //返回岛屿数量
        }
    }
}
