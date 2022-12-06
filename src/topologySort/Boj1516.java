package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[] times = new int[n + 1]; // 건물을 짓는데 걸리는 시간
        int[] indegree = new int[n + 1]; // 미리 지어야하는 건물 수
        ArrayList<Integer>[] afterBuild = new ArrayList[n + 1]; // 이 뒤에 지을 수 있는 건물들
        for (int i = 1; i <= n; i++) {
            afterBuild[i] = new ArrayList<Integer>();
        }
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {
            int[] tmp = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            times[i] = tmp[0];
            indegree[i] = tmp.length - 2;
            for (int j = 1; j < tmp.length - 1; j++) {
                // tmp[j] 번 건물을 지은 뒤에 i 번 건물을 지을 수 있다
                afterBuild[tmp[j]].add(i);
            }
            if (indegree[i] == 0) queue.add(i);
        }

        long[] answers = new long[n + 1]; // 대기시간
        while (!queue.isEmpty()) {
            Integer now = queue.poll(); // 지금 이거 건설!

            for (Integer next : afterBuild[now]) {
                indegree[next] -= 1;
                answers[next] = Math.max(answers[next], answers[now] + times[now]);

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(answers[i] + times[i]);
        }

    }
}
