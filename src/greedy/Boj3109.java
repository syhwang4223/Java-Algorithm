package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj3109 {

    static int r, c;
    static char[][] map;
    static boolean[][] visited;
    static int answer;
    static int[][] directions = {{-1, 1}, {0, 1}, {1, 1}}; // 대각선 위, 오른쪽, 대각선 아래

    // 그리디 + dfs
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());

        // 지도 정보 입력
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        // 0 번째 열에서 시작
        visited = new boolean[r][c];

        answer = 0;

        for (int i = 0; i < r; i++) {
            Point point = new Point(i, 0);
            if (!point.isValidRange()) continue;
            point.setVisited(true);
            dfs(point);
        }

        System.out.println(answer);

    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void setVisited(boolean tf) {
            visited[row][col] = tf;
        }

        public boolean isValidRange() {
            if (0 <= row && row < r && 0 <= col && col < c && map[row][col] == '.') return true;
            else return false;
        }

        public boolean isvisited() {
            if (visited[row][col]) return true;
            else return false;
        }
    }

    static boolean dfs(Point point) {
        // 마지막 열에 도착
        if (point.col == c - 1) {
            answer += 1;
            // 빵집을 만난 루트다
            return true;
        }

        // 원웅이네 빵집 방향으로 이동
        for (int[] direction : directions) {
            Point next = new Point(point.row + direction[0], point.col + direction[1]);
            if (next.isValidRange() && !next.isvisited()) {
                next.setVisited(true);

                // 이미 빵집을 만난 루트면 더 진행x
                if (dfs(next)) return true;
            }
        }
        return false;
    }
}
