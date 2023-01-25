package MST.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4386 별자리 만들기
public class Boj4386 {
    static int n;
    static Star[] stars;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        stars = new Star[n];
        visited = new boolean[n];

        // 별의 좌표 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars[i] = new Star(i, x, y);
        }

        System.out.println(makeConstellation());
    }

    // prim 알고리즘
    static double makeConstellation() {
        PriorityQueue<StarLine> pq = new PriorityQueue<>();
        pq.add(new StarLine(stars[0], stars[0])); // 0번 별을 시작점으로 선택

        double cost = 0;

        while (!pq.isEmpty()) {
            StarLine now = pq.poll();

            // 이미 연결된 별이면 패스
            if (visited[now.b.num]) continue;

            // 별자리 연결
            visited[now.b.num] = true;
            cost += now.getDist();

            for (int i = 0; i < n; i++) {
                // 아직 연결되지 않은 별자리를 pq 에 추가
                if (!visited[i]) {
                    pq.add(new StarLine(now.b, stars[i]));
                }
            }
        }
        
        return cost;
    }

    static class Star {
        int num;
        double x, y;

        public Star(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    static class StarLine implements Comparable<StarLine>{
        Star a, b;

        // 두 별 사이의 거리 계산
        public double getDist() {
            return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        }

        public StarLine(Star a, Star b) {
            this.a = a;
            this.b = b;
        }

        // 가중치가 작은 간선부터 연결
        @Override
        public int compareTo(StarLine o) {
            return this.getDist() >= o.getDist() ? 1 : -1;
        }
    }


}
