package divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Boj2263 {

    static StringBuilder sb = new StringBuilder();
    static List<Integer> inOrder;
    static List<Integer> postOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        inOrder = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        postOrder = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        // 분할정복
        traversal(0, n - 1, 0, n - 1);

        // 정답 출력
        System.out.println(sb.toString());
    }


    static void traversal(int inOrderLeft, int inOrderRight, int postOrderLeft, int postOrderRight) {

        // 범위 밖
        if (inOrderLeft > inOrderRight || postOrderLeft > postOrderRight) return;

        // postOrder 의 마지막 값이 root
        int root = postOrder.get(postOrderRight);

        // root 탐색
        sb.append(root);
        sb.append(" ");

        int inOrderRoot = inOrder.indexOf(root);
        int size = inOrderRoot - 1 - inOrderLeft;

        // 왼쪽 자식 탐색
        traversal(inOrderLeft, inOrderRoot - 1
                , postOrderLeft, postOrderLeft + size);

        // 오른쪽 자식 탐색
        traversal(inOrderRoot + 1, inOrderRight
                , postOrderLeft + size + 1, postOrderRight - 1);

    }

}
