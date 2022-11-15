package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2186 {

    static int n, m, k;
    static char[][] board;
    static char[] word;
    static int answer;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // n, m, k 입력
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        // 문자판 입력
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = bufferedReader.readLine().toCharArray();
        }

        // 만들어야 할 단어
        word = bufferedReader.readLine().toCharArray();

        // 유효한 방문인지 메모
        dp = new int[n][m][word.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < word.length; k++) {
                    // 미방문 -1, 유효하지 않은 방문 0, 유효한 방문 1
                    dp[i][j][k] = -1;
                }
            }
        }

        answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word[0]) {
                    answer += dfs(i, j, 0);
                }
            }
        }

        System.out.println(answer);
    }

    static int dfs(int row, int col, int index) {

        if (index == word.length - 1) return dp[row][col][index] = 1;
        if (dp[row][col][index] != -1) return dp[row][col][index];

        dp[row][col][index] = 0;

        for (int[] direction : directions) {
            for (int i = 1; i <= k; i++) {
                int nextRow = row + i * direction[0];
                int nextCol = col + i * direction[1];

                // 범위 밖
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;

                if (board[nextRow][nextCol] == word[index + 1]) {
                    dp[row][col][index] += dfs(nextRow, nextCol, index + 1);
                }
            }
        }
        return dp[row][col][index];
    }
}
