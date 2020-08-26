package packagename;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
//        LeetCode200 test = new LeetCode200();
//        char[][] grid = {{'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}};
//        int res = test.numIslandsBFS(grid);
//        System.out.println(res);

        POJ_3984 test = new POJ_3984();
        char[][] grid = {{'1', '1', '0', '0', '1'},
                        {'1', '1', '0', '1', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'}};
        int[] start = new int[]{0, 3};
        int[] destination = new int[]{3, 4};
        int res = test.minimumMoves(grid, start, destination);
        System.out.println(res);
    }
}

class LeetCode15 {
    /*
    3Sum (O(n^2))
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 去重
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(nums[i] > 0){
                break;
            }
            int left = i+1;
            int right = nums.length-1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left+1]){
                        left ++;
                    }
                    while(left < right && nums[right] == nums[right-1]){
                        right --;
                    }
                    left += 1;
                    right -= 1;
                }
                else if(sum < 0){
                    left += 1;
                }
                else if(sum > 0){
                    right -= 1;
                }
            }
        }
        return res;
    }
}

class LeetCode18 {
    /*
    4Sum
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        if (nums == null || length < 4) return res;
        Arrays.sort(nums);
        // 第一个指针循环
        for (int i = 0; i < length-3; i++) {
            // 重复情况continue
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            // 获取当前情况能取到的最小值，如果最小值都大于target,那么return
            if (target < nums[i] + nums[i+1] + nums[i+2] + nums[i+3]){
                break;
            }
            //获取当前情况能取到的最大值，如果最大值小于target,那么continue
            if (target > nums[i] + nums[length-1] + nums[length-2] + nums[length-3]){
                continue;
            }
            // 第二个指针循环，其实就是3Sum了
            for (int j = i+1; j < length-2; j++) {
                // 重复情况continue
                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                // 获取当前情况能取到的最小值，如果最小值都大于target,那么return
                if (target < nums[i] + nums[j] + nums[j+1] + nums[j+2]){
                    break;
                }
                //获取当前情况能取到的最大值，如果最大值小于target,那么continue
                if (target > nums[i] + nums[length-1] + nums[length-2] + nums[j]){
                    continue;
                }
                int left = j+1;
                int right = length-1;
                while(left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target){
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while(left < right && nums[left] == nums[left+1]){
                            left += 1;
                        }
                        while(left < right && nums[right] == nums[right-1]){
                            right -= 1;
                        }
                        left += 1;
                        right -= 1;
                    }
                    else if (sum > target){
                        right -= 1;
                    }
                    else if (sum < target){
                        left += 1;
                    }
                }
            }
        }
        return res;
    }
}

class LeetCode200 {
    /*
    岛屿数量
    由 '1'（陆地）和 '0'（水）组成的的二维网格，计算网格中岛屿的数量。
     */

    public int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    // 法1：dfs
    public void dfs(char[][] grid, int x, int y){
        // 将现在的位置信息替换
        grid[x][y] = '0';
        // 遍历可以移动的方向
        int nr = grid.length;
        int nc = grid[0].length;
        for (int i = 0; i < directions.length; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];
            if (nx >= 0 && nx < nr && ny >= 0 && ny < nc && grid[nx][ny] == '1') {
                dfs(grid, nx, ny);
            }
        }
        return;
    }

    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res += 1;
                }
            }
        }
        return res;
    }

    //法2： bfs:主要是保存状态，首先把初始状态保存到队列里，然后从队列的最前端不断的取状态，并把
    //         该状态可以转移到的状态中尚未访问过的部分加入队列，直到队列为空或者找到解
    public int numIslandsBFS(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int count = 0;
        boolean[][] marked = new boolean[rows][cols];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && marked[i][j] == false) {
                    count += 1;
                    LinkedList<Integer> queue = new LinkedList<>();
                    queue.addLast(i * cols + j);
                    marked[i][j] = true;
                    // 取队列最前端的状态
                    while (!queue.isEmpty()){
                        int cur = queue.removeFirst();
                        int curX = cur / cols;
                        int curY = cur % cols;
                        // 加入可以转移到的状态
                        for (int k = 0; k < directions.length; k++) {
                            int nx = curX + directions[k][0];
                            int ny = curY + directions[k][1];
                            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && grid[nx][ny] == '1' && marked[nx][ny] == false) {
                                queue.addLast(nx * cols + ny);
                                marked[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}

class POJ_3984 {
    /*
    迷宫中的最短路径
    宽度优先遍历按照 距开始状态由近及远的顺序进行搜索，因此很容易求出最短路径和最少操作之类。
     */
    public int minimumMoves(char[][] grid, int[] start, int[] destination) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        // dist 保存到达任意点的最短距离
        for (int [] i : dist) {
            Arrays.fill(i, -1);
        }
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(start);
        dist[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            for (int[] direction :directions) {
                int x = cur[0] + direction[0];
                int y = cur[1] + direction[1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '0' && dist[x][y] < 0) {
                    queue.addLast(new int[]{x, y});
                    dist[x][y] = dist[cur[0]][cur[1]] + 1;
                }
            }
        }
        return dist[destination[0]][destination[1]];
    }
}
class LeetCode31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}






