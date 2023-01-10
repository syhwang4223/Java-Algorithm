package graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2206
public class Boj2206 {
    static int n, m;
    static int[][] map;
    static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        // 지도 입력
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs(new Pos(0, 0, 1, 0)));

    }

    static int bfs(Pos pos) {

        // visited[0][i][j]: 벽을 부수지 않았을 때, visited[1][i][j]: 벽을 안부쉈을 때
        boolean[][][] visited = new boolean[2][n][m];

        Queue<Pos> queue = new LinkedList<>();
        queue.add(pos);

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            if (now.row == n - 1 && now.col == m - 1) return now.dist;

            for (int[] direction : directions) {
                int nextRow = now.row + direction[0];
                int nextCol = now.col + direction[1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;

                // 뚫려 있는 경우
                if (map[nextRow][nextCol] == 0){
                    if (!visited[now.destroy][nextRow][nextCol]) {
                        queue.add(new Pos(nextRow, nextCol, now.dist + 1, now.destroy));
                        visited[now.destroy][nextRow][nextCol] = true;
                    }
                }

                // 벽이고 아직 벽을 부순 적이 없는 경우
                else if (now.destroy == 0) {
                    // 해당 벽을 부수고 방문한 적 없으면 방문
                    if (!visited[1][nextRow][nextCol]) {
                        visited[1][nextRow][nextCol] = true;
                        queue.add(new Pos(nextRow, nextCol, now.dist + 1, 1));
                    }
                }
            }
        }

        return -1;
    }

    static class Pos {
        int row, col;
        int dist;
        int destroy;

        public Pos(int row, int col, int dist, int wall) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.destroy = wall;
        }
    }

}
