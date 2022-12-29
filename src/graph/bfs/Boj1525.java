package graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1525 {

    static int[][] puzzle;
    static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static final String complete = "123456780";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 퍼즐 입력
        String puzzle = "";
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                puzzle += st.nextToken();
            }
        }

        // 초기 상태로 이미 완성
        if (puzzle.equals(complete)) {
            System.out.println(0);
            System.exit(0);
        }

        Queue<String> queue = new LinkedList<String>();

        // 퍼즐 배열, 이동 횟수
        Map<String, Integer> check = new HashMap<String, Integer>();

        // 초기 상태
        queue.add(puzzle.toString());
        check.put(puzzle.toString(), 0);
        int answer = -1;

        while (!queue.isEmpty()) {
            String now = queue.poll();

            // 비어있는 칸의 위치
            int blankIdx = now.indexOf('0');
            int row = blankIdx / 3;
            int col = blankIdx % 3;

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];
                if (nextRow < 0 || nextRow >= 3 || nextCol < 0 || nextCol >= 3) {
                    // 범위 밖
                    continue;
                }

                int swapIdx = 3 * nextRow + nextCol; // 옮길 곳의 2차원 인덱스를 1차원 인덱스로

                // 빈 칸으로 수 이동
                StringBuilder next = new StringBuilder(now);
                char swap = next.charAt(swapIdx);
                next.setCharAt(swapIdx, '0');
                next.setCharAt(blankIdx, swap);
                int cnt = check.get(now) + 1;


                String nextString = next.toString();

                // 이미 확인한 배열이면 패스
                if (check.containsKey(nextString)) {
                    continue;
                }

                // 정리 끝난 경우
                if (nextString.equals(complete)) {
                    System.out.println(cnt);
                    System.exit(0);
                }


                // 다음 탐색
                check.put(nextString, cnt);
                queue.add(nextString);
            }
        }

        System.out.println(answer);

    }
}
