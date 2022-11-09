package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1504 {

    final static int INF = 200000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        // n, m 입력
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        ArrayList<Edge>[] maps = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            maps[i] = new ArrayList<>();
        }

        // 버스 정보 입력
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            maps[start].add(new Edge(end, cost));
            maps[end].add(new Edge(start, cost));
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int v1 = Integer.parseInt(stringTokenizer.nextToken());
        int v2 = Integer.parseInt(stringTokenizer.nextToken());

        int[] fromStart = djikstra(n, queue, maps, 1);
        int[] fromV1 = djikstra(n, queue, maps, v1);
        int[] fromV2 = djikstra(n, queue, maps, v2);

        int cost1 = fromStart[v1] + fromV1[v2] + fromV2[n];
        int cost2  = fromStart[v2] + fromV2[v1] + fromV1[n];

        if (cost1 < INF && cost2 < INF) {
            System.out.println(Integer.min(cost1, cost2));
        } else if (cost1 < INF) {
            System.out.println(cost1);
        } else if (cost2 < INF) {
            System.out.println(cost2);
        } else {
            System.out.println("-1");
        }

    }

    private static int[] djikstra(int n, PriorityQueue<Edge> queue, ArrayList<Edge>[] maps, int start) {
        // 1번 노드에서 출발
        int[] costs = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(costs, INF);
        costs[start] = 0;
        queue.add(new Edge(start, 0));

        // 그래프 탐색
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            int curNode = cur.vertex;
            int weight = cur.weight;

            if (visited[curNode]) continue;
            visited[curNode] = true;

            for (Edge edge : maps[curNode]) {
                int adjNode = edge.vertex;
                int cost = edge.weight;

                if (costs[adjNode] > costs[curNode] + cost) {
                    costs[adjNode] = costs[curNode] + cost;
                    queue.add(new Edge(adjNode, costs[adjNode]));
                }
            }
        }
        return costs;
    }

    static class Edge implements Comparable<Edge> {

        int vertex, weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
