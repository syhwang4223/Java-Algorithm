package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2239 스도쿠
public class Boj2239 {
    static int[][] sudoku;
    static List<Pos> blanks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 스도쿠 퍼즐 입력
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            sudoku[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 빈 자리를 모두 리스트에 넣는다
                if (sudoku[i][j] == 0) blanks.add(new Pos(i, j));
            }
        }

        // 첫 빈칸 부터 순서대로 탐색
        solve(0);
    }

    static void solve(int idx) {
        // 빈칸 다 채웠으면 탐색 종료
        if (idx == blanks.size()) {
            printSudoku();
            // 사전식으로 앞서는 것을 출력해야 하므로 더는 탐색x
            System.exit(0);
        }

        int row = blanks.get(idx).row;
        int col = blanks.get(idx).col;

        // 1 ~ 9 중 넣을 수 있는 숫자를 찾는다
        boolean[] possible = new boolean[10];
        Arrays.fill(possible, true);

        // 가로줄
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] != 0) possible[sudoku[row][i]] = false;
        }

        // 세로줄
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] != 0) possible[sudoku[i][col]] = false;
        }

        // 9 칸 배열
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int r = (row/3)*3 + i;
                int c = (col/3)*3 + j;
                if (sudoku[r][c] != 0) possible[sudoku[r][c]] = false;
            }
        }

        // 넣을 수 있는 숫자를 넣어본다
        for (int i = 1; i <= 9; i++) {
            if (possible[i]) {
                sudoku[row][col] = i; // 숫자 넣기
                solve(idx + 1); // 다음 빈 칸으로 넘어간다
                sudoku[row][col] = 0; // 칸 되돌리기
            }
        }

    }

    static void printSudoku() {
        for (int[] ints : sudoku) {
            for (int a : ints) {
                System.out.print(a);
            }
            System.out.println();
        }
    }

    static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
