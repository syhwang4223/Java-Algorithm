package simulation;

// https://school.programmers.co.kr/learn/courses/30/lessons/60059
public class Prog_자물쇠와열쇠 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}
                , new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
    }

    static class Solution {
        public boolean solution(int[][] key, int[][] lock) {

            int len = 2 * key.length + lock.length - 2;
            int[][] map = new int[len][len]; // 자물쇠 + 열쇠 한번에 둘 확장 배열

            // 확장 배열에 자물쇠 두기
            for (int i = 0; i < lock.length; i++) {
                for (int j = 0; j < lock.length; j++) {
                    map[i + key.length - 1][j + key.length - 1] = lock[i][j];
                }
            }

            // 시계방향으로 돌려가며 하나라도 맞으면 된다
            for (int i = 0; i < 4; i++) {

                if (match(key, map)) return true;

                // 열쇠 회전해서 다시 시도
                if (i == 3) continue;
                key = rotateClockwise90(key);
            }

            return false;
        }

        public boolean match(int[][] key, int[][] map) {

            int m = map.length;
            int n = key.length;

            // 열쇠를 어딘가에 놓았을 때 lock 배열이 전부 1이 되면 된다.
            // 밖으로 넘어가는건 상관 없고 lock 에 0 이나 2가 없이 전부 1이면 된다.

            // 열쇠 둘 위치
            for (int i = 0; i < m - n + 1; i++) {
                for (int j = 0; j < m - n + 1; j++) {

                    // 열쇠 놓는다
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            map[i + k][j + l] += key[k][l];
                        }
                    }

                    // 자물쇠가 다 1로 채워졌나 확인
                    boolean flag = true;
                    for (int k = n - 1; k < m - n + 1; k++) {
                        for (int l = n - 1; l < m - n + 1; l++) {
                            if (map[k][l] != 1) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) break;
                    }

                    // 열쇠가 자물쇠랑 잘 맞으면 끝
                    if (flag) return true;

                    // 아니면 다시 돌려놔야 한다
                    // 열쇠 놓는다
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            map[i + k][j + l] -= key[k][l];
                        }
                    }
                }
            }

            return false;
        }


        // 2차원 배열을 시계방향으로 90도 회전
        public int[][] rotateClockwise90(int[][] key) {
            int n = key.length;

            int[][] rotateKey = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rotateKey[j][n - i - 1] = key[i][j];
                }
            }

            return rotateKey;
        }
    }
}
