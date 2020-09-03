package packagename;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.*;

public class AlgorithmCode {
    public static void main(String[] args) {
        LeetCode79 test = new LeetCode79();

        char[][] grid = {{'C', 'A', 'A'},
                         {'A', 'A', 'A'},
                         {'B', 'C', 'D'}};
        String word = "AAB";
        System.out.println(test.exist(grid, word));



//        POJ_3984 test = new POJ_3984();
//        char[][] grid = {{'1', '1', '0', '0', '1'},
//                        {'1', '1', '0', '1', '0'},
//                        {'1', '1', '0', '0', '0'},
//                        {'0', '0', '0', '0', '0'}};
//        int[] start = new int[]{0, 3};
//        int[] destination = new int[]{3, 4};
//        int res = test.minimumMoves(grid, start, destination);
//        System.out.println(res);

//        LeetCode40 test = new LeetCode40();
//        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
//        int target = 8;
//        List<List<Integer>> res = test.combinationSum2(nums, target);
//        System.out.println(res);


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

class LeetCode46 {
    /*
    全排列1
    给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     */
    public List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (length == 0) {
            return res;
        }
        boolean[] used = new boolean[length];
        Deque<Integer> path = new ArrayDeque<>();

        dfs(nums, length, 0, path, used, res);
        return res;
    }
    private void dfs(int[] nums, int length, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums, length, depth+1, path, used, res);
                used[i] = false;
                path.removeLast();
            }
        }
    }
}

class LeetCode47 {
    /*
    全排列2
    给定一个可包含重复数字的序列，返回所有不重复的全排列。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (length == 0) {
            return res;
        }

        boolean[] used = new boolean[length];
        Deque<Integer> path = new ArrayDeque<>(length);

        Arrays.sort(nums);
        dfs(nums, 0, length, used, path, res);
        return res;
    }
    private void dfs(int[] nums, int depth, int length, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (!used[i]){
                if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false) {
                    continue;
                }
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums, depth+1, length, used, path, res);
                used[i] = false;
                path.removeLast();
            }
        }
        return;
    }
}

class LeetCode31 {
    /*
    next permutation
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // [4,5,2,3,1] --> 4 5 3 2 1 --> 4 5 3 1 2
        int i = nums.length - 2;
        while (i >= 0 && nums[i] > nums[i + 1]) {
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

class LeetCode60 {
    /*
    给定 n 和 k，按大小顺序列出所有排列情况, 返回第 k 个排列。
     */
    public String getPermutation(int n, int k) {
        StringBuffer res = new StringBuffer();
        if (n <= 0 || k <= 0) {
            return res.toString();
        }
        Deque<String> path = new ArrayDeque<>();
        boolean[] used = new boolean[n];
        dfs(0, n, k, path, used, res);
        return res.toString();
    }
    private void dfs(int start, int n, int k, Deque<String> path, boolean[] used, StringBuffer res) {
        if (path.size() == n) {
            for (String v : path) res.append(v);
            return;
        }
        int cur = factorial(n - start - 1); //要放在垂直层循环外面，表示该水平层的排列数目
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                if (cur < k) {
                    k -= cur;
                    continue; //后续不需要撤回操作，因为没有执行前面的操作，均跳过了
                }
                path.addLast(i+1 + "");
                used[i] = true;
                dfs(start+1, n, k, path, used, res);
            }
        }
    }
    private int factorial(int n) {
        int res = 1;
        if (n == 0 || n == 1) return res;
        while (n > 0) {
            res *= n;
            n -= 1;
        }
        return res;
    }
}

class LeetCode39 {
    /*
    组合总和
    给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
    candidates中的数字可以无限制重复被选取。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int length = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, length, path, target, res);
        return res;
    }

    private void dfs(int[] candidates, int start, int length, Deque<Integer> path, int target, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            dfs(candidates, i, length, path, target-candidates[i], res);
            path.removeLast();
        }
    }
}

class LeetCode40 {
    /*
    组合总和2
    给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
    candidates中的每个数字在每个组合中只能使用一次。
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int length = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, length, path, target, res);
        return res;
    }
    private void dfs(int[] candidates, int start, int length, Deque<Integer> path, int target, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < length; i++) {
            // 不满足条件的直接剪支
            if (target - candidates[i] < 0) {
                break;
            }
            // 同层的遇到相同的选择，剪支
            if (i > start && candidates[i] == candidates[i-1]) {
                continue;
            }
            path.addLast(candidates[i]);
            dfs(candidates, i+1, length, path, target-candidates[i], res);
            path.removeLast();
        }
    }
}

class LeetCode77 {
    /*
    组合：给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, n, k, path, res);
        return res;
    }
    private void dfs(int start, int n, int k, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            //剩余数字不够了，直接剪支
            if (i + k - path.size() - 1 > n) {
                break;
            }
            path.addLast(i);
            dfs(i+1, n, k, path, res);
            path.removeLast();
        }
    }
}

class LeetCode78 {
    /*
    子集：给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, 0, path, res);
        return res;
    }
    private void  dfs(int[] nums, int start, Deque<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        if (start == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            path.addLast(nums[i]);
            dfs(nums, i+1, path, res);
            path.removeLast();
        }
    }
}

class LeetCode90 {
    /*
    子集2：给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(nums);
        dfs(nums, 0, path, res);
        return res;
    }
    private void  dfs(int[] nums, int start, Deque<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        if (start == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
            path.addLast(nums[i]);
            dfs(nums, i+1, path, res);
            path.removeLast();
        }
    }
}

class LeetCode93 {
    /*
    复原IP地址
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int length = s.length();
        if (s == null || length < 4 || length > 12) {
            return res;
        }
        List<String> path = new ArrayList<>();
        int splitUsed = 0;
        dfs(s, 0, length, splitUsed, path, res);
        return res;
    }
    private void dfs(String s, int start, int length, int splitUsed, List<String> path, List<String> res) {
        if (splitUsed == 4) {
            if (start == length) {
                String temp = "";
                for (String k :path) {
                    temp = temp + k + '.';
                }
                temp = temp.substring(0, temp.length()-1);
                res.add(temp);
            }else {
                return;
            }
        }
        for (int i = 1; i <= 3; i++) {
            if (start + i > length) return;
            if ( i > 1 && s.charAt(start) == '0') return;

            String tempStr = s.substring(start, start + i);
            if (Integer.parseInt(tempStr) > 255) return;

            path.add(tempStr);
            splitUsed += 1;
            dfs(s, start + i, length, splitUsed, path, res);
            path.remove(path.size()-1);
            splitUsed -= 1;
        }
    }
}

class LeetCode17 {
    /*
    电话号码2-9的字母组合
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        int length = digits.length();
        if (length == 0) {
            return res;
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        StringBuffer path = new StringBuffer();
        dfs(0, digits, length, path, map, res);
        return res;
    }
    // 23
    private void dfs(int start, String digits, int length, StringBuffer path, Map<Character, String> map, List<String> res) {
        if (path.length() == length) {
            res.add(path.toString());
            return;
        }
        String tempStr = map.get(digits.charAt(start));
        for (int i = 0; i < tempStr.length(); i++) {
            Character c = tempStr.charAt(i);
            path.append(c);
            dfs(start+1, digits, length, path, map, res);
            path.deleteCharAt(path.length()-1);
        }
    }
}

class LeetCode784 {
    /*
    字母大小写全排列
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        int length = S.length();
        if (length == 0) {
            return res;
        }
        Deque<Character> path = new ArrayDeque<>();
        dfs(0, length, S, path, res);
        return res;
    }
    private void dfs(int start, int length, String S, Deque<Character> path, List<String> res) {
        if (start == length) {
            String s = new String();
            for (Character v: path) {
                s += v;
            }
            res.add(s);
            return;
        }
        StringBuffer tempCh = new StringBuffer();
        char cur = S.charAt(start);
        if (cur >= 'A' && cur <= 'Z') {
            tempCh.append(cur);
            tempCh.append((char) (cur+'a'-'A'));
        }else if (cur >= 'a' && cur <= 'z') {
            tempCh.append(cur);
            tempCh.append((char) (cur-'a'+'A'));
        }else {
            tempCh.append(cur);
        }
        for (int i = 0; i < tempCh.length(); i++) {
            path.addLast(tempCh.charAt(i));
            dfs(start+1, length, S, path, res);
            path.removeLast();
        }
    }
}

class LeetCode22 {
    /*
    有效括号生成
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        int left = n, right = n;
        StringBuffer path = new StringBuffer();
        dfs(left, right, path, res);
        return res;
    }
    private void dfs(int left, int right, StringBuffer path, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(path.toString());
            return;
        }
        if (left < 0) {
            return;
        }else if(right < 0) {
            return;
        }
        if (left > right) {
            return;
        }
        path.append('(');
        left -= 1;
        dfs(left, right, path, res);
        path.deleteCharAt(path.length()-1);
        left += 1;

        path.append(')');
        right -= 1;
        dfs(left, right, path, res);
        path.deleteCharAt(path.length()-1);
        right += 1;
    }

}

class LeetCode733 {
    /*
    给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int rows = image.length;
        int cols = image[0].length;
        int srcColor = image[sr][sc];
        boolean[][] visited = new boolean[rows][cols];
        dfs(image, sr, sc, newColor, srcColor, visited);
        return image;

    }
    private void dfs(int[][] image, int x, int y, int newColor, int srcColor, boolean[][] visited) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
            return;
        }
        if (image[x][y] == srcColor && visited[x][y] == false) {
            image[x][y] = newColor;
            visited[x][y] = true;
            dfs(image, x+1, y, newColor, srcColor, visited);
            dfs(image, x-1, y, newColor, srcColor, visited);
            dfs(image, x, y+1, newColor, srcColor, visited);
            dfs(image, x, y-1, newColor, srcColor, visited);
        }
    }

}

class LeetCode130 {
    /*
    被围绕的区域：给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     */
    public void solve(char[][] board) {
        int rows = board.length;
        if (rows == 0 || board == null) {
            return;
        }
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == board.length-1 || j == 0 || j == board[0].length-1) {
                    dfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '0') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'Z') {
                    board[i][j] = '0';
                }
            }
        }
    }
    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] == '0') {
            board[i][j] = 'Z';
            dfs(board, i, j+1);
            dfs(board, i, j-1);
            dfs(board, i+1, j);
            dfs(board, i-1, j);
        }
        return;
    }
}

class LeetCode79 {
    /*
    单词搜索
     */
    public boolean exist(char[][] board, String word) {
        if (word == "" || board == null) {
            return false;
        }
        int rows = board.length;
        if (rows == 0) {
            if (word == "") {
                return true;
            } else {
                return false;
            }
        }

        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        boolean flag = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    flag = dfs(0, i, j, word, board, visited);
                    if (flag) {
                        return flag;
                    }
                }
            }
        }
        return flag;
    }
    private boolean dfs(int start, int i, int j, String word, char[][] board, boolean[][] visited) {
        if (start == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] == word.charAt(start) && !visited[i][j]) {
            visited[i][j] = true;
            boolean temp_flag = dfs(start+1, i+1, j, word, board, visited) ||
                    dfs(start+1, i-1, j, word, board, visited) ||
                    dfs(start+1, i, j+1, word, board, visited) ||
                    dfs(start+1, i, j-1, word, board, visited);
            if (temp_flag) {
                return temp_flag;
            } else {
                visited[i][j] = false;
                return temp_flag;
            }

        }
        return false;
    }
}
