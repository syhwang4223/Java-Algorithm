package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1135 {
    static int n;
    static int[] parent; // 직속 상사
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        parent = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 트리 초기화
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n; i++) {
            // 직속 부하 업데이트
            if (parent[i] != -1) nodes[parent[i]].addChild(i);
        }


        System.out.println(call(0));

    }

    static int call(int now) {

        // 직속 부하 중 전달이 가장 오래 걸리는 직원한테 먼저 전달


        ArrayList<Integer> childTimes = new ArrayList<Integer>();
        for (Integer child : nodes[now].children) {
            childTimes.add(call(child));
        }

        childTimes.sort(Comparator.reverseOrder());

        int time = 0;
        for (int i = 0; i < childTimes.size(); i++) {
            time = Math.max(time, childTimes.get(i) + i + 1);
        }

        return time;
    }

    static class Node {

        int num;
        ArrayList<Integer> children = new ArrayList<Integer>();

        public Node(int num) {
            this.num = num;
        }

        public void addChild(int child) {
            children.add(child);
        }

    }

}
