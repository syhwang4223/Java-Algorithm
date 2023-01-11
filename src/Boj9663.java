import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9663
public class Boj9663 {
    static int answer;
    static boolean[][] queen;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        answer = 0;
        for (int i = 0; i < n; i++) {
            queen = new boolean[n][n]; // 퀸을 놓은 위치 초기화
            nQueen(0, i, 1); // 첫번째 행의 모든 위치에 놓아본다
        }

        // 정답 출력
        System.out.println(answer);
    }

    static void nQueen(int row, int col, int cnt) {

        // 퀸을 놓는다. 못놓는 자리면 넘어간다.
        if (!dropQueen(row, col)) return;

        // n 개의 퀸을 다 놓았으면 정답 수 상승
        if (cnt == n) {
            answer += 1;
            queen[row][col] = false; // 퀸 돌려놓기
            return;
        }

        // 마지막 행이면 탐색 끝
        if (row == n - 1) return;

        // 아니면 다음 행으로 넘어간다
        for (int i = 0; i < n; i++) {
            nQueen(row + 1, i, cnt + 1);
        }

        // 퀸을 다시 원래대로 돌려놓는다
        queen[row][col] = false;
    }

    // 서로 공격할 수 없는 자리면 퀸을 놓는다
    static boolean dropQueen(int row, int col) {

        for (int i = 0; i < n; i++) {
            // 같은 열에 퀸이 있는 경우 못놓음
            if (queen[i][col]) return false;

            // 같은 행에 퀸이 있는 경우 못놓음
            if (queen[row][i]) return false;
        }

        // 대각선 방향 탐색
        int offset = 1;
        while (isInBoard(row + offset, col + offset)) {
            if (queen[row + offset][col + offset]) return false;
            offset += 1;
        }
        offset = 1;
        while (isInBoard(row - offset, col - offset)) {
            if (queen[row - offset][col - offset]) return false;
            offset += 1;
        }

        offset = 1;
        while (isInBoard(row + offset, col - offset)) {
            if (queen[row + offset][col - offset]) return false;
            offset += 1;
        }
        offset = 1;
        while (isInBoard(row - offset, col + offset)) {
            if (queen[row - offset][col + offset]) return false;
            offset += 1;
        }

        // 같은 행, 열, 대각선에 모두 퀸이 없으면 놓는다
        queen[row][col] = true;
        return true;
    }

    static boolean isInBoard(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}
