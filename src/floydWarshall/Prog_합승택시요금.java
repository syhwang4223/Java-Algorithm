package floydWarshall;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/72413#
public class Prog_합승택시요금 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
        System.out.println(solution.solution(7, 3, 4, 1, new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}));
        System.out.println(solution.solution(6, 4, 5, 6, new int[][]{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}));
    }

    static class Solution {
        public int solution(int n, int s, int a, int b, int[][] fares) {

            int[][] dist = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                Arrays.fill(dist[i], 100001 * n);
                dist[i][i] = 0;
            }

            for (int[] fare : fares) {
                dist[fare[0]][fare[1]] = fare[2];
                dist[fare[1]][fare[0]] = fare[2];
            }

            // 플로이드 와샬
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            // 각각 택시타고 간다고 했을 때 비용
            int answer = dist[s][a] + dist[s][b];

            // i를 공통으로 경유해서 가는게 싸면 그렇게 한다
            for (int i = 1; i <= n; i++) {
                answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
            }

            return answer;
        }
    }
}
