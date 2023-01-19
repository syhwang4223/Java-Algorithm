package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1799 비숍
public class Boj1799 {
    static int n;
    static int[][] board;
    static boolean[][] visited;
    static final int[][] offsets = {{-1, -1}, {-1, 1}};
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 체스판 입력
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][n];

        answer = new int[2];
        solve(-1, 0, true); // 검은칸 탐색
        solve(-1, 0, false); // 흰칸 탐색

        System.out.println(answer[0] + answer[1]);
    }

    static void solve(int index, int cnt, boolean isBlack) {

        for (int nextIndex = index + 1; nextIndex < n * n; nextIndex++) {
            int row = nextIndex / n;
            int col = nextIndex % n;

            // (0, 0) 이 검은칸이라고 가정하면 row + col 이 짝수면 검은칸, 홀수면 흰칸
            boolean color = (row + col) % 2 == 0;

            // 비숍을 둘 수 없는 곳이거나 탐색중인 색의 칸이 아니면 패스
            if (color != isBlack || !canDrop(row, col)) continue;

            // 비숍을 놓는다
            visited[row][col] = true;
            solve(nextIndex, cnt + 1, isBlack);
            visited[row][col] = false; // 말 되돌리기
        }

        int answerIdx = isBlack ? 0 : 1;
        answer[answerIdx] = Math.max(answer[answerIdx], cnt);
    }

    // 해당 자리에 비숍을 놓을 수 있는지 대각선 방향 체크
    static boolean canDrop(int row, int col) {

        // 빈자리가 아니면 못놓음
        if (board[row][col] != 1) return false;

        // 어차피 왼쪽 위부터 비숍을 놓고 있기 때문에 아래쪽에는 비숍이 없다
        // 따라서 왼쪽 위 대각선과 오른쪽 위 대각선 방향만 체크
        for (int[] offset : offsets) {
            int nextRow = row;
            int nextCol = col;

            while (true) {
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) break;
                if (visited[nextRow][nextCol]) return false;

                nextRow += offset[0];
                nextCol += offset[1];
            }
        }

        return true;
    }
}
