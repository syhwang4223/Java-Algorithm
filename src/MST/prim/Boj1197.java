package MST.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1197 최소 스패닝 트리
public class Boj1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] edges = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) edges[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 양방향 간선 입력
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        // 정답 출력
        System.out.println(getMstCost(edges, v));
    }

    // 프림 알고리즘으로 최소 스패닝 트리의 토탈 가중치 계산
    static int getMstCost(ArrayList<Edge>[] edges, int v) {
        boolean[] visited = new boolean[v + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>(); // 가중치가 작은 순으로
        pq.add(new Edge(1, 0)); // 1 번 노드를 시작점으로 선택

        int total = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            // 방문한 적 있는 노드는 패스
            if (visited[now.dest]) continue;

            // 간선 연결
            visited[now.dest] = true;
            total += now.cost;

            for (Edge next : edges[now.dest]) {
                if (!visited[next.dest]) pq.add(next);
            }
        }

        return total;
    }

    static class Edge implements Comparable<Edge>{
        int dest;
        int cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

}
