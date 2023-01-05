package MST.Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1647 {

    static int[] parent;
    static int e; // 현재까지 간선의 개수
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Load> loads = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            loads.add(new Load(a, b, c));
        }

        // 일단 최소 스패닝 트리 만들고 나서
        // 거기서 불필요한 제일 높은 코스트의 도로 하나 빼고 나면 마을이 두개로 분리된다
        // 그러니까 트리의 간선이 n - 2 개가 될 때까지만 만들면 된다


        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 크루스칼 알고리즘
        while (e < n - 2) {
            union(loads.poll());
        }

        System.out.println(answer);

    }

    static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(Load load) {
        int a = load.a;
        int b = load.b;

        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
            e += 1;
            answer += load.cost;
        }
    }

    static class Load implements Comparable<Load>{
        int a;
        int b;
        int cost;

        public Load(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Load o) {
            return this.cost - o.cost;
        }
    }
}
