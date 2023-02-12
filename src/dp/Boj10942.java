package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/10942 팰린드롬?
public class Boj10942 {
    static int n, m;
    static int[] numbers;
    static boolean[][] isPalindrome;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 칠판에 숫자 n 개
        n = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        isPalindrome = new boolean[n + 1][n + 1];
        dp();

        // 질문 m 개
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // 팰린드롬인 경우엔 1, 아닌 경우엔 0 출력
            sb.append(isPalindrome[s][e] ? "1\n" : "0\n");
        }

        System.out.println(sb);
    }

    static void dp() {

        for (int i = 1; i <= n; i++) {
            // 길이가 1인 팰린드롬
            isPalindrome[i][i] = true;

            // 길이가 2인 팰린드롬
            if (i == n) continue;
            if (numbers[i] == numbers[i + 1]) isPalindrome[i][i + 1] = true;
        }

        // 길이가 3이상인 팰린드롬
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start + len <= n; start++) {
                int end = start + len;

                // start+1 ~ end-1 까지가 팰린드롬이고 numbers[start]==numbers[end] 이면 팰린드롬이다
                if (isPalindrome[start + 1][end - 1] && numbers[end] == numbers[start]) {
                    isPalindrome[start][end] = true;
                }
            }
        }
    }
}
