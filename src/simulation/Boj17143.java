package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17143 낚시왕
public class Boj17143 {
    static int r, c, m;
    static Shark[][] map;
    static int answer;
    static final int[][] directions = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new Shark[r + 1][c + 1];

        // 상어 정보 입력
        for (int i = 0; i < m; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[tmp[0]][tmp[1]] = new Shark(tmp[2], tmp[3], tmp[4]);
        }

        // 낚시
        fishing();

        System.out.println(answer);
    }

    static void fishing() {

        // 1 열부터 마지막 열까지 낚시왕 이동
        for (int king = 1; king <= c; king++) {

            // 땅에서 가장 가까운 상어를 잡는다
            for (int i = 1; i <= r; i++) {
                if (map[i][king] != null) {
                    answer += map[i][king].size; // 잡으면
                    map[i][king] = null; // 상어 사라짐
                    break;
                }
            }

            // 상어 이동
            moveShark();

        }
    }

    static void moveShark() {

        // 이동했을 때 상태
        Shark[][] moved = new Shark[r + 1][c + 1];

        // 상어 이동
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {

                Shark shark = map[i][j];

                // 맵에 남은 모든 상어 이동
                if (shark != null) {

                    map[i][j] = null;

                    int move = shark.speed; // 상어의 이동 거리
                    boolean upDown = shark.dir == 1 || shark.dir == 2;

                    if (upDown) {
                        // 상하로 이동
                        move %= ((r - 1) * 2);

                    } else {
                        // 좌우로 이동
                        move %= ((c - 1) * 2);
                    }

                    // 이동
                    int row = i, col = j; // 상어 위치
                    while (move > 0) {

                        // 경계에 닿으면 방향 전환
                        if (shark.dir == 1 && row == 1 || shark.dir == 2 && row == r
                         || shark.dir == 3 && col == c || shark.dir == 4 && col == 1) shark.changeDir();

                        row += directions[shark.dir][0];
                        col += directions[shark.dir][1];


                        move -= 1;
                    }

                    // 이동한 자리에 다른 상어 없거나 얘가 더 크면 얘가 자리 먹는다
                    if (moved[row][col] == null || shark.size > moved[row][col].size) {
                        moved[row][col] = shark;
                    }
                }
            }
        }

        // 상어 위치 업데이트
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                map[i][j] = moved[i][j];
            }
        }
    }

    static class Shark {
        int speed, dir, size;

        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        public void changeDir() {
            if (dir == 1) dir = 2;
            else if (dir == 2) dir = 1;
            else if (dir == 3) dir = 4;
            else dir = 3;
        }

    }
}
