package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/7579 앱
public class Boj7579 {
    static int n, m;
    static int[] memory, cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 앱 정보 입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution());
    }

    static int solution() {
        int sum = Arrays.stream(cost).sum(); // 가능한 최대 비용
        int[] dp = new int[sum + 1]; // dp[i]: i 의 비용으로 확보할 수 있는 최대 메모리

        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }

        for (int i = 0; i <= sum; i++) {
            if (dp[i] >= m) return i; // 메모리 m 확보 가능한 최소 비용 반환
        }

        return -1; // 문제 조건 상 -1 이 반환되는 경우는 없긴 함
    }
}
