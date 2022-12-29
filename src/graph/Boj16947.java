package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16947 {

    static int n;
    static int[] parent;
    static int[] distance;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static boolean[] isCycle;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        n = Integer.parseInt(bufferedReader.readLine());

        // 인접 배열
        adj = new ArrayList[n + 1];
        for (int i = 1; i<=n;i++) adj[i] = new ArrayList<Integer>();

        // 역 정보 입력
        for (int i = 1; i <= n; i++) {

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }


        // 순환선 찾기
        isCycle = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];

            // 사이클 찾으면 탐색 종료
            if (checkCycle(i, -1, i)) break;
        }

        queue = new LinkedList<Integer>();

        distance = new int[n + 1];
        Arrays.fill(distance, -1);
        for (int i = 1; i <= n; i++) {
            if (isCycle[i]) {
                distance[i] = 0;
                queue.add(i);
            }
        }

        // 모든 역에서 순환선까지 거리 계산
        bfs();

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(distance[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    // dfs 로 순환선 판별
    static boolean checkCycle(int start, int prev, int now) {

        visited[now] = true;

        // 연결된 노드 순회
        for (Integer next : adj[now]) {
            // 탐색하다가 사이클 찾았으면 이 노드도 사이클에 속해 있는 것
            if (!visited[next]) {
                if (checkCycle(start, now, next)) return isCycle[next] = true;
            }
            // 직전에 방문했던 노드가 아닌데 시작 노드면 싸이클 발생한 것
            else if (next != prev && next == start) return isCycle[next] = true;
        }

        return false;
    }


    // bfs 로 순환선까지의 거리 계산
    static void bfs() {

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : adj[now]) {
                if (distance[next] == -1) {
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
            }

        }
    }

}
