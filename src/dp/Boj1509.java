package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1509 팰린드롬 분할
public class Boj1509 {

    static int[] dp;
    static boolean[][] palindrome;
    static int n;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 값 초기화
        n = s.length();
        arr = s.toCharArray();
        dp = new int[n + 1]; // dp[i]: 0~i번째 글짜까지 팰린드롬 분할의 최솟값
        palindrome = new boolean[n + 1][n + 1]; // palindrome[i][j] : subString(i,j) 가 팰린드롬인지

        setPalindrome();
        dp();

        System.out.println(dp[n]);
    }

    static void dp() {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // j~idx가 팰린드롬이면 dp[idx] = min(dp[idx], dp[j-1] + 1)
        for (int idx = 1; idx <= n; idx++) {
            for (int j = 1; j <= idx; j++) {

                if (palindrome[j][idx]) {
                    dp[idx] = Math.min(dp[idx], dp[j - 1] + 1);
                }
            }
        }
    }

    static void setPalindrome() {

        // i부터 j까지 문자열이 팰린드롬인지 체크
        // 최대 2500 글자라 다 돌아도 될듯?
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {

                boolean isPalindrome = true;
                int left = i - 1, right = j - 1;

                while (left <= right) {
                    if (arr[left] != arr[right]) {
                        isPalindrome = false;
                        break;
                    }
                    left += 1;
                    right -= 1;
                }

                palindrome[i][j] = isPalindrome;
            }
        }
    }
}
