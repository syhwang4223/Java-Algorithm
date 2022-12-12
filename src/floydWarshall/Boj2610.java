package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2610 {
    static int n, k;
    static int[][] relations;
    static boolean[] check;
    static ArrayList<ArrayList<Integer>> groups;
    static ArrayList<Integer> answers;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        n = Integer.parseInt(bufferedReader.readLine());
        k = Integer.parseInt(bufferedReader.readLine());

        // 관계 정보 입력
        relations = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(relations[i], 101); // 서로 모르는 사이
            relations[i][i] = 0; // 자기 자신
        }
        int a, b;
        for (int i = 0; i < k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            a = Integer.parseInt(stringTokenizer.nextToken());
            b = Integer.parseInt(stringTokenizer.nextToken());
            // a 와 b 가 서로 알고 있다 (양방향 관계)
            relations[a][b] = 1;
            relations[b][a] = 1;
        }

        // 플로이드 와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    relations[i][j] = Math.min(relations[i][j], relations[i][k] + relations[k][j]);
                }
            }
        }

        // 연결된 사이끼리 그룹을 나눈다
        groups = new ArrayList<>();
        check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                check[i] = true;
                ArrayList<Integer> group = new ArrayList<Integer>();
                group.add(i);
                for (int j = 1; j <= n; j++) {
                    if (0 < relations[i][j] && relations[i][j] < 101) {
                        // 아는 사이면 그룹에 넣는다
                        group.add(j);
                        check[j] = true;
                    }
                }
                // 완성된 그룹을 그룹 리스트에 추가
                groups.add(group);
            }
        }

        // 그룹별로 의사 전달 시간이 가장 짧은 사람을 대표로 뽑는다
        answers = new ArrayList<Integer>();
        // 그룹 별로 전부 확인
        for (ArrayList<Integer> group : groups) {
            int maxTime = Integer.MAX_VALUE; // 의사 전달 시간 중 최댓값
            int r = group.get(0); // 대표

            // 모든 멤버 전부 확인
            for (Integer rep : group) {
                int time = 0; // rep 가 대표일 때 의사 전달 시간 중 최댓값
                for (int i : relations[rep]) {
                    // i: 의사 전달 시간
                    if (i > 100) continue;
                    time = Math.max(time, i);
                }
                if (time <= maxTime) {
                    // 의사 전달 시간 중 최댓값이 가장 작은 사람이 대표가 된다
                    maxTime = time;
                    r = rep;
                }
            }
            // 그룹의 대표를 정답 리스트에 추가
            answers.add(r);
        }

        // 정답 출력
        System.out.println(answers.size());
        answers.sort(Comparator.naturalOrder());
        for (Integer answer : answers) {
            System.out.println(answer);
        }
    }

}
