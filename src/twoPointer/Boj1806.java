package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1806 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 입력
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int s = Integer.parseInt(stringTokenizer.nextToken());
        int[] arr = Arrays.stream((bufferedReader.readLine() + " 0").split(" ")).mapToInt(Integer::parseInt).toArray();


        // 투 포인터
        int left = 0, right = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        while (left <= right && right <= n) {
            if (sum >= s) {
                answer = Math.min(answer, right - left);
                sum -= arr[left++];
            }

            else {
                sum += arr[right++];
            }
        }

        if (answer == Integer.MAX_VALUE) answer = 0;
        System.out.println(answer);

    }
}
