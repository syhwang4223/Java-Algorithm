package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2623 {
    static int n, m;
    static int[] indegree; // indegree[i]: i번 가수 전에 불러야하는 가수 수
    static ArrayList<Integer>[] singers; // singers[i]: i 번 가수 후에 부를 가수들
    static ArrayList<Integer> solution;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer =  new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        // 리스트 초기화
        singers = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            singers[i] = new ArrayList<>();
        }
        indegree = new int[n + 1];


        // 가수 순서 입력
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int num = Integer.parseInt(stringTokenizer.nextToken()); // 보조 PD 가 담당한 가수 수

            int s1 = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 1; j < num; j++) {
                int s2 = Integer.parseInt(stringTokenizer.nextToken());
                singers[s1].add(s2);
                indegree[s2] += 1;
                s1 = s2;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        solution = new ArrayList<>();

        // 앞서 불러야하는 가수가 없는 가수들을 먼저 부르게 한다
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            solution.add(now);

            for (int next : singers[now]) {
                // 진입 차수 감소
                indegree[next] -= 1;

                // 먼저 불러야하는 가수 없으면 부르게 한다
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }

        }

        // 출력
        if (solution.size() < n) {
            System.out.println(0);
        } else {
            for (Integer i : solution) {
                System.out.println(i);
            }
        }
    }

}
