package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16724 {
    static int n, m;
    static char[][] map;
    static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R
    static Map<Character, Integer> direction = new HashMap<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도 정보 입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 한 경로, 사이클에 SAFE ZONE 한 개 씩만 있으면 모두 SAFE ZONE 으로 들어갈 수 있다
        // 즉, 연결된 구역? 경로? 의 개수를 묻는 문제

        initDirections();
        parent = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }

        // union find
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 이동 방향
                Integer dir = direction.get(map[i][j]);

                // 편의상 1차원 인덱스 사용
                int nowIndex = m * i + j; // 현재 위치
                int nextIndex = m * (i + move[dir][0]) + (j + move[dir][1]); // 피리 듣고 이동할 위치

                // 둘은 같은 그룹
                union(nowIndex, nextIndex);
            }
        }

        // 집합 개수 집계
        Set<Integer> parentSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                parentSet.add(find(m * i + j));
            }
        }

        System.out.println(parentSet.size());
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

    static void initDirections() {
        direction.put('U', 0);
        direction.put('D', 1);
        direction.put('L', 2);
        direction.put('R', 3);
    }

}
