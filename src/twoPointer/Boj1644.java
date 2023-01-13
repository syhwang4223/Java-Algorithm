package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://www.acmicpc.net/problem/1644
public class Boj1644 {
    static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        getPrimes(n);
        System.out.println(solution(n));

    }

    // 투 포인터로 부분합이 n이 되는 범위 계산
    static int solution(int n) {
        int answer = 0;

        int left = 0, right = 0;
        int sum = 0;

        while (right < primes.size()) {

            if (sum >= n) {
                if (n == sum) answer += 1;

                left += 1;
                sum -= primes.get(left);
            } else {
                right += 1;
                if (right >= primes.size()) break;
                sum += primes.get(right);
            }
        }

        return answer;
    }

    // n 이하의 소수 리스트 구하기
    static void getPrimes(int n) {
        primes.add(0); // 인덱스 맞추기 용도

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) primes.add(i);
        }
    }

    // n 이 소수인지 판별
    static boolean isPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
