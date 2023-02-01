package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9527 {

    static long a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        System.out.println(count(b) - count(a - 1));

    }

    // 1 ~ a 까지 1 의 개수 합
    static long count(long a) {
        if (a == 0 || a == 1) return a;

        long pow = 1;
        int n = 0;

        while (pow * 2 <= a) {
            pow *= 2;
            n += 1;
        }

        // 1 ~ 2^n 까지 1의 개수 = n * 2^(n-1)

        // 2^n <= a <= 2^(n+1) 인 a 에 대하여 2^n ~ a 까지 1의 개수는?

        // f(a) = a.bitCount() 라고 했을 때
        // f(a) = f(a - 2^n) + 1
        // -> f(2^n) + ... + f(a) = count(a - 2^n) + (a - 2^n + 1)

        // 따라서 count(a) = count(2^n - 1) + f(2^n) + ... + f(a) = n*2^(n-1) + count(a - 2^n) + (a - 2^n + 1)

        return n * pow / 2 + count(a - pow) + (a - pow + 1);
    }
}
