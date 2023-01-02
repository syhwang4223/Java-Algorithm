package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2250 {
    static int n;
    static Node[] tree;
    static int[] min, max; // level 별 x 좌표의 최대, 최소값
    static int x; // 현재 x 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        tree = new Node[n + 1];
        min = new int[n + 1];
        max = new int[n + 1];

        // 트리 초기화
        for (int i = 1; i <= n; i++) {
            tree[i] = new Node(i, -1, -1);
            min[i] = n;
            max[i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int num = Integer.parseInt(stringTokenizer.nextToken());
            int left = Integer.parseInt(stringTokenizer.nextToken());
            int right = Integer.parseInt(stringTokenizer.nextToken());

            // 노드의 자식 업데이트
            tree[num].left = left;
            tree[num].right = right;

            // 자식이 존재하면, 자식 노드 쪽에서도 부모 업데이트
            if (left != -1) tree[left].parent = num;
            if (right != -1) tree[right].parent = num;
        }

        // 루트 찾기
        int root = 0;
        for (int i = 1; i <= n; i++) {
            // 부모 노드 없는 노드가 최상위 노드
            if (tree[i].parent == -1) {
                root = i;
                break;
            }
        }

        // 중위 순회
        inOrder(root, 1);

        // 가장 큰 너비 찾기
        int maxLevel = 1;
        int maxWidth = 0;
        for (int i = 1; i <= n; i++) {
            int width = max[i] - min[i] + 1;
            if (width > maxWidth) {
                maxWidth = width;
                maxLevel = i;
            }
        }

        // 출력
        System.out.println(maxLevel + " " + maxWidth);
    }

    static void inOrder(int root, int level) {
        Node now = tree[root];

        // left
        if (now.left != -1) inOrder(now.left, level + 1);

        // root
        min[level] = Math.min(min[level], x);
        max[level] = x;
        x += 1;

        // right
        if (now.right != -1) inOrder(now.right, level + 1);
    }

    static class Node {
        int parent;
        int num;
        int left, right;

        public Node(int num, int left, int right) {
            this.parent = -1;
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }

}
