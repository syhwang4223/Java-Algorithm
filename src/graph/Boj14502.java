package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj14502 {
    static int n, m;
    static int[][] map;
    static List<Pos> virus = new ArrayList<>();
    static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 연구소 지도 입력
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            // 0: 빈칸, 1: 벽, 2: 바이러스
            st =  new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (map[i][j] == 2) virus.add(new Pos(i, j));
            }
        }

        buildWall(0);
        System.out.println(answer);
    }

    static void buildWall(int cnt) {
        if (cnt == 3) {
            // 벽 세 개 세웠으면 안전구역 계산한다
            answer = Math.max(answer, safetyAreas());
            return;
        }

        // 벽 덜 세웠으면 마저 세운다
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (map[i][j] == 0) {
                    map[i][j] = 1; // 빈 칸에 벽을 세운다
                    buildWall(cnt + 1); // 마저 세운다
                    map[i][j] = 0; // 원래대로 돌려놓는다
                }
            }
        }
    }

    static int safetyAreas() {
        boolean[][] visited = new boolean[n][m];

        // 바이러스 전파용 복제 배열
        int[][] virusMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                virusMap[i][j] = map[i][j];
            }
        }

        Queue<Pos> q = new LinkedList<>(virus);

        // 바이러스 전파
        while (!q.isEmpty()) {
            Pos now = q.poll();
            visited[now.row][now.col] = true;

            for (int[] direction : directions) {
                int nextRow = now.row + direction[0];
                int nextCol = now.col + direction[1];

                if (nextRow < 0 || nextRow >=n || nextCol < 0 || nextCol >=m) continue;

                if (!visited[nextRow][nextCol] && virusMap[nextRow][nextCol] == 0) {
                    virusMap[nextRow][nextCol] = 2;
                    q.add(new Pos(nextRow, nextCol));
                }
            }
        }

        // 남은 빈 공간 계산
        int area = 0;
        for (int[] m : virusMap) {
            for (int i : m) {
                if (i == 0) area += 1;
            }
        }

        return area;
    }

    static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
