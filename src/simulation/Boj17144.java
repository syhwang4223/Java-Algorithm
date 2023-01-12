package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17144
public class Boj17144 {
    static int r, c, t;
    static int[][] map;
    static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        int y = 0;

        // 방 정보 입력
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) y = i; // 공기 청정기의 y 축 위치
            }
        }

        // t 초 동안 작동
        for (int i = 0; i < t; i++) {
            spread(); //  미세 먼지 확산
            cleaning(y - 1); // 공기청정기 가동
        }

        // 결과 출력
        System.out.println(getDust());

    }

    static void cleaning(int airCond) {

        int top = airCond;
        int bottom = airCond + 1;
        
        // 위쪽 공기청정기의 바람은 반시계방향 순환,
        // 아래로 당기기
        for (int i = top - 1; i > 0; i--)
            map[i][0] = map[i-1][0];
        // 왼쪽으로 당기기
        for (int i = 0; i < c - 1; i++)
            map[0][i] = map[0][i+1];
        // 위로 당기기
        for (int i = 0; i < top; i++)
            map[i][c - 1] = map[i + 1][c - 1];
        // 오른쪽으로 당기기
        for (int i = c - 1; i > 1; i--)
            map[top][i] = map[top][i-1];
        // 공기청정기에서 부는 바람은 미세먼지가 없는 바람
        map[top][1] = 0;

        // 아래쪽 공기청정기의 바람은 시계방향으로 순환
        // 위로 당기기
        for (int i = bottom + 1; i < r - 1; i++)
            map[i][0] = map[i + 1][0];
        // 왼쪽으로 당기기
        for (int i = 0; i < c - 1; i++)
            map[r - 1][i] = map[r - 1][i + 1];
        // 아래로 당기기
        for (int i = r - 1; i > bottom; i--)
            map[i][c - 1] = map[i - 1][c - 1];
        // 오른쪽으로 당기기
        for (int i = c - 1; i > 1; i--)
            map[bottom][i] = map[bottom][i - 1];
        // 공기청정기에서 부는 바람은 미세먼지가 없는 바람
        map[bottom][1] = 0;

    }

    static void spread() {

        Queue<Dust> queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) queue.add(new Dust(i, j, map[i][j]));
            }
        }

        while (!queue.isEmpty()) {
            Dust dust = queue.poll();

            for (int[] direction : directions) {
                int nextRow = dust.row + direction[0];
                int nextCol = dust.col + direction[1];

                if (nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;

                int d = dust.amount / 5;

                // 옆으로 먼지 확산
                if (map[nextRow][nextCol] != -1) {
                    map[nextRow][nextCol] += d;
                    map[dust.row][dust.col] -= d;
                }
            }
        }

    }

    static int getDust() {

        int answer = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                answer += map[i][j];
            }
        }

        return answer + 2;
    }

    static class Dust {
        int row, col;
        int amount;

        public Dust(int row, int col, int amount) {
            this.row = row;
            this.col = col;
            this.amount = amount;
        }
    }

}
