package priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Boj1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 문제 정보 입력
        int n = Integer.parseInt(bufferedReader.readLine());
        Problem[] problems = new Problem[n];
        for (int i = 0; i < n; i++) {
            int[] tmp = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            problems[i] = new Problem(tmp[0], tmp[1]);
        }
        Arrays.sort(problems);

        // 큐의 원소의 개수 = 푼 문제 수
        // 큐의 원소 = 풀고 얻은 컵라면 수
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Problem problem : problems) {
            //
            if (problem.deadline > pq.size()) {
                // 데드라인이 지나지 않은 문제는 푼다
                pq.add(problem.cup);
            } else {
                if (pq.peek() < problem.cup) {
                    // 지금 푼 문제의 컵라면 수가 더 이득이면 이전 문제 빼고 지금 문제를 넣는다
                    pq.poll();
                    pq.add(problem.cup);
                }
            }
        }

        int answer = 0;
        for (Integer p : pq) {
            answer += p;
        }
        System.out.println(answer);
    }

    static class Problem implements Comparable<Problem>{
        int deadline, cup;

        public Problem(int deadline, int cup) {
            this.deadline = deadline;
            this.cup = cup;
        }

        @Override
        public int compareTo(Problem o) {
            // 데드라인 오름차순 정렬, 같으면 컵라면 내림차순 정렬
            return this.deadline == o.deadline ? o.cup - this.cup : this.deadline - o.deadline;
        }
    }

}
