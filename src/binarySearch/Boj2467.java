package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2467 {
    static int n;
    static int[] solutions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         n = Integer.parseInt(br.readLine());
         solutions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

         solve();
    }

    private static void solve() {
        int left = 0, right = solutions.length - 1;
        int minSum = Integer.MAX_VALUE;
        int[] answer = new int[2];

        // 이분탐색
        while (left < right) {

            // 용액 합 업데이트
            int sum = solutions[left] + solutions[right];
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                answer[0] = solutions[left];
                answer[1] = solutions[right];
            }

            if (sum == 0) {
                // 합이 0이면 더 볼 것 없음
                System.out.println(answer[0] +  " " + answer[1]);
                System.exit(0);

            } else if (sum > 0) {
                right -= 1;
            } else {
                left += 1;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
