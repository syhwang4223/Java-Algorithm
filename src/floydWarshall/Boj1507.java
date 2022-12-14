package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1507 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 도로 정보 입력
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] maps = new int[n][n];
        for (int i = 0; i < n; i++) {
            maps[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] answers = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answers[i][j] = maps[i][j];
            }
        }

        // 같은 시간 다른 경로로 대체할 수 있는 길은 지운다
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || i == k || k == j) continue;
                    if (maps[i][j] == maps[i][k] + maps[k][j]) {
                        answers[i][j] = 0;
                        answers[j][i] = 0;
                    }
                    // 모순이 생기는 경우
                    // (입력된 값보다 더 빠른 길이 존재할 경우)
                    else if (maps[i][j] > maps[i][k] + maps[k][j]) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
        }


        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                answer += answers[i][j];
            }
        }

        System.out.println(answer);
    }
}
