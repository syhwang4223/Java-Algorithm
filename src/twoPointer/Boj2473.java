package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int n = Integer.parseInt(bufferedReader.readLine());
        long[] solutions = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Arrays.sort(solutions);
        long min = Long.MAX_VALUE;
        long[] answer = new long[3];

        // i : 첫 번째 용액
        for (int i = 0; i < n - 2; i++) {

            // 투 포인터
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                long sum = solutions[i] + solutions[left] + solutions[right];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer[0] = solutions[i];
                    answer[1] = solutions[left];
                    answer[2] = solutions[right];
                }

                if (sum < 0) left += 1;
                else right -= 1;

            }
        }

        Arrays.sort(answer);

        for (long i : answer) {
            System.out.print(i + " ");
        }

    }
}
