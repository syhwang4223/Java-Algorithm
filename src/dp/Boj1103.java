package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj1103 {

    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int n, m;
    static char[][] board;
    static int[][] dp;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        // 보드 입력
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = bufferedReader.readLine().toCharArray();
        }

        // dp, 방문 여부 배열 설정
        dp = new int[n][m];
        visited = new boolean[n][m];

        int answer = 1;

        if (Integer.parseInt(String.valueOf(board[0][0])) >= Math.max(n, m)) {
            // 처음부터 못움직이는 경우
            System.out.println(answer);
            System.exit(0);
        } else {

            // 0, 0 에서 출발
            visited[0][0] = true;
            dfs(0, 0, 1);

            // 최대값 계산
            for (int[] i : dp) {
                for (int j : i) {
                    answer = Math.max(j, answer);
                }
            }

            System.out.println(answer);
        }

    }

    static void dfs(int row, int col, int depth) {

        dp[row][col] = depth;

        for (int[] direction : directions) {
            int nextRow = row + direction[0] * Integer.parseInt(String.valueOf(board[row][col]));
            int nextCol = col + direction[1] * Integer.parseInt(String.valueOf(board[row][col]));

            // 범위 밖이거나 구멍이면 패스
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m || board[nextRow][nextCol] == 'H') continue;

            // 동전을 이동해봤자 다른 경로로 이동시켰던게 더 게임 횟수가 많은 경우
            if (dp[nextRow][nextCol] > depth) continue;


            // 한 번 방문했던 위치를 또 방문한다 = 루프가 존재한다 = 무한 번 움직일 수 있다
            if (visited[nextRow][nextCol]) {
                System.out.println("-1");
                System.exit(0);
            }


            // 방문
            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, depth + 1);
            visited[nextRow][nextCol] = false;
        }
    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
