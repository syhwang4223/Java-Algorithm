package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2342 Dance Dance Revolution
public class Boj2342 {

    static int[] ddr;
    static final int[][] cost = {
            {0, 2, 2, 2, 2},
            {9, 1, 3, 4, 3},
            {9, 3, 1, 3, 4},
            {9, 4, 3, 1, 3},
            {9, 3, 4, 3, 1}
    };
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ddr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[5][5][ddr.length - 1]; // dp[i][j][k]: k번째 커맨드를 왼발이 i, 오른발이 j로 밟았을때 힘의 합 최소

        // dp 값 초기화
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        System.out.println(ddr(0, 0, 0));
    }

    static int ddr(int left, int right, int index) {

        if (index == ddr.length - 1) return 0;
        int command = ddr[index];

        if (dp[left][right][index] < Integer.MAX_VALUE) return dp[left][right][index];

        // 왼발 옮기는것과 오른발 옮기는 것 중 힘이 덜드는 값으로 갱신
        return dp[left][right][index] = Math.min(ddr(command, right, index + 1) + cost[left][command], ddr(left, command, index + 1) + cost[right][command]);
    }
}
