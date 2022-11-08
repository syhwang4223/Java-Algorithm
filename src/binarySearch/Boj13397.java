package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj13397 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        int[] arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int max= 1, min = 10000;
        for (int i : arr) {
            if (i > max) max = i;
            if (i < min) min = i;
        }

        int left = 0;
        int right = max - min;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            // 구간의 개수가 m 개 이하인지 판단
            int count = 1;
            min = 10000;
            max = 1;
            for (int i = 0; i < n; i++) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
                if (max - min > mid) {
                    count += 1;
                    min = 10000;
                    max = 1;
                    i -= 1;
                }
            }

            // 구간의 개수 만족하면 정답이 될 수 있음
            if (count <= m) {
                answer = mid;
                right = mid - 1;
            }

            // 아니면 답 키우기
            else left = mid + 1;
        }

        System.out.println(answer);

    }
}
