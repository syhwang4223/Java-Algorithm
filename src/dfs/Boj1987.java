package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj1987 {

    static int r, c;
    static char[][] board;
    static Set<Character> visited;
    static int answer;
    static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());

        // 보드 입력
        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = bufferedReader.readLine().toCharArray();
        }

        // 방문한 적 있는 알파벳
        visited = new HashSet<>();

        // 0, 0 에서 시작
        visited.add(board[0][0]);
        answer = 1;
        dfs(0, 0, 1);

        System.out.println(answer);
    }

    static void dfs(int row, int col, int depth) {
        answer = Math.max(answer, depth);

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            // 보드 범위 밖은 패스
            if (nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;

            // 방문한 적 없는 알파벳이면 방문
            if (!visited.contains(board[nextRow][nextCol])) {
                visited.add(board[nextRow][nextCol]);
                dfs(nextRow, nextCol, depth + 1);
                visited.remove(board[nextRow][nextCol]);
            }
        }
    }
}
