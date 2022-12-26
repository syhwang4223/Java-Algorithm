package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj4803 {
    static BufferedReader bufferedReader;
    static int[] parent;
    static boolean[] hasCycle;

    public static void main(String[] args) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = 1;

        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            if (n == 0 && m == 0) break;

            testcase(t++, n, m);
        }
    }

    static void testcase(int t, int n, int m) throws IOException {

        // 부모, 사이클 존재 여부 초기화
        init(n);

        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            union(Math.min(a, b), Math.max(a, b));
        }

        // 각 노드의 root 갱신
        for (int i = 1; i <= n; i++) {
            parent[i] = find(i);
        }

        // 루트의 개수만큼 연결된 집합이 존재한다
        HashSet set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            // 사이클 없는 집합이면 트리
            if (!hasCycle[parent[i]]) set.add(parent[i]);
        }

        int answer = set.size();

        System.out.print("Case " + t + ": ");
        if (answer > 1) {
            System.out.printf("A forest of %d trees.\n", answer);
        } else if (answer == 1) {
            System.out.println("There is one tree.");
        } else {
            System.out.println("No trees.");
        }
    }

    static void init(int n) {
        hasCycle = new boolean[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        } else {
            hasCycle[a] = true;
        }
        if (hasCycle[b]) hasCycle[a] = true;
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}
