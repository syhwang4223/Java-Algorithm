package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1937 {

    static int[][] dp;
    static int[][] map;
    static int n;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {

        // 맵 정보 입력
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // dp
        dp = new int[n][n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = dfs(i, j);
                answer = Integer.max(answer, count);
            }
        }


        System.out.println(answer);
    }

    static int dfs(int x, int y) {

        // 이미 계산한 위치는 패스
        if (dp[x][y] > 0) return dp[x][y];

        dp[x][y] = 1;

        // 상하좌우 돌면서
        for (int[] dir : direction) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];

            // 범위 밖은 패스
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;

            // 현재 위치보다 더 대나무가 많은 곳이면 이동 가능
            if (map[nextX][nextY] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nextX, nextY) + 1);
            }

        }

        return dp[x][y];
    }
}
