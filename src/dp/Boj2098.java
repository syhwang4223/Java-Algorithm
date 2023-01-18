package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2098 외판원 순회
public class Boj2098 {

    static int n;
    static int[][] map;
    static int[][] dp;
    static final int NOT_CYCLE = 16 * 1000000 + 1;
    static final int INF = 2 * NOT_CYCLE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];


        // 지도 정보 입력
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // dp[i][j]: i는 현재 방문한 도시, j는 지금까지 방문한 도시 비트마스크 일 때, 나머지 도시들까지 방문하고 다시 출발 도시로 돌아올 때 드는 최소 비용
        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        // 0 번 마을부터 방문
        System.out.println(tsp(0, 1));
    }

    /**
     * @param now: 현재 위치한 도시
     * @param visited: 방문한 도시
     */
    static int tsp(int now, int visited) {

        // 모든 도시를 방문했으면 탐색 종료
        if (visited == (1 << n) - 1) {
            // 시작 도시로 돌아갈 수 없는 경우
            if (map[now][0] == 0) {
                return NOT_CYCLE;
            } else {
                return map[now][0];
            }
        }

        // 이미 값을 계산해놓은 경우
        if (dp[now][visited] < INF) {
            return dp[now][visited];
        }

        // 현재 도시에서 이동 가능한 도시 탐색
        for (int next = 0; next < n; next++) {

            // 이미 방문했거나 길이 없는 경우 패스
            if ((visited & (1 << next)) != 0 || map[now][next] == 0) continue;

            // now -> next 로 간 후 next 에서 남은 마을들을 방문하는 값이 더 작으면 dp 값 갱신
            int cost = tsp(next, visited | (1 << next)) + map[now][next];
            dp[now][visited] = Math.min(dp[now][visited], cost);

        }

        return dp[now][visited];
    }
}
