package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1256 {

    static String answer = "";
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        long k = Long.parseLong(stringTokenizer.nextToken());

        dp = new long[n + 1][m + 1];

        if (check(n, m) < k) {
            System.out.println("-1");
        } else {
            make(n, m, k);
            System.out.println(answer);
        }
    }
    public static void make(int a, int z, long k) {
        // a 개수 남은거 없으면 뒤에 무조건 z 를 붙인다
        if (a == 0) {
            for (int i = 0; i < z; i++)
                answer += "z";
            return;
        }
        // z 개수 남은거 없으면 뒤에 무조건 a 를 붙인다
        if (z == 0) {
            for (int i = 0; i < a; i++)
                answer += "a";
            return;
        }

        // 일단 뒤에 a 를 붙였다 가정해보는데
        long check = check(a-1, z);
        if (k > check) {
            // k > check 면 a 로 시작했을 때 k번째 문자열을 못만든다는 뜻이므로
            // a 가 아니라 z 를 뒤에 붙인다
            answer += "z";
            make(a, z - 1, k - check);
        } else {
            // a 로 시작했을 때 k번재 문자열을 만들 수 있으면 a를 뒤에 붙인다
            answer += "a";
            make(a - 1, z, k);
        }
    }

    // n 개의 'a' 와 m 개의 'z' 를 줄세우는 경우의 수
    // == (n+m)!/n!m!
    // => 점화식: f(n,m) = f(n-1,m) + f(n,m-1)
    public static long check(int a, int z) {
        if (a == 0 || z == 0) {
            return 1;
        }
        if (dp[a][z] != 0) {
            return dp[a][z];
        }
        // 어차피 k 의 최댓값보다만 크면 되는거니 오버플로 안나게
        return dp[a][z] = Long.min(check(a - 1, z) + check(a, z - 1), 1000000001);
    }

}
