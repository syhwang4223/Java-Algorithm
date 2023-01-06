package graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2638 {
    static int n, m;
    static int[][] map;
    static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static boolean[][] isOutside;

    static int time; // 경과 시간
    static Queue<Pos> melt = new LinkedList<>(); // 녹일 치즈들
    static ArrayList<Pos> cheese = new ArrayList<>(); // 아직 안녹은 치즈들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 치즈 배열 입력
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheese.add(new Pos(i, j));
            }
        }

        // 빈 공간인데 외부인지 치즈 안쪽인지 구분하기
        // [결과] 외부: 2, 치즈: 1, 치즈 안쪽: 0

        // 가장자리는 치즈 없댔음
        isOutside = new boolean[n][m];
        isOutside[0][0] = true;
        checkOutside(new Pos(0, 0));


        time = 0;

        // 치즈 다 사라질 때까지 녹이기
        while (!cheese.isEmpty()) {
            melt();
            time += 1;
        }

        System.out.println(time);

    }

    // bfs
    static void checkOutside(Pos pos) {

        Queue<Pos> air = new LinkedList<>();
        air.add(pos);

        while (!air.isEmpty()) {

            Pos now = air.poll();

            for (int[] dir : directions) {
                int nextRow = now.row + dir[0];
                int nextCol = now.col + dir[1];

                // 범위 밖
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;

                // 방문 안한 곳이고 빈 공간이면 탐색
                if (map[nextRow][nextCol] == 0 && !isOutside[nextRow][nextCol]) {
                    isOutside[nextRow][nextCol] = true;
                    air.add(new Pos(nextRow, nextCol));
                }
            }
        }
    }

    // bfs
    static void melt() {

        for (Pos c : cheese) {
            int cnt = 0; // 외부와 닿은 면 수

            for (int[] dir : directions) {

                int nextRow = c.row + dir[0];
                int nextCol = c.col + dir[1];

                // 범위 밖
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;

                if (map[nextRow][nextCol] == 0 && isOutside[nextRow][nextCol]) cnt += 1;
            }

            // 두 변 이상 외부 공기에 접촉하면 사라진다
            // 근데 당장 녹으면 안되고 이번 타임에 녹을 애들을 다음타임으로 바뀔 때 한번에 녹여야 함
            if (cnt >= 2) {
                melt.add(c);
            }
        }

        // 치즈 녹이기
        while (!melt.isEmpty()) {
            Pos poll = melt.poll();
            map[poll.row][poll.col] = 0;
            cheese.remove(poll);

            // 그리고 녹아서 치즈가 바깥으로 뚫린 경우 다시 체크!
            isOutside[poll.row][poll.col] = true;
            checkOutside(poll);
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
