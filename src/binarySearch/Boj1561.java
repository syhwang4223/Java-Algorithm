package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1561 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        long n = Long.parseLong(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[] rides = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 이분탐색
        // 탐색 대상: 모든 아이가 놀이기구를 탈 수 있는 최소 시간
        long l = 0;
        long r = n * Arrays.stream(rides).max().getAsInt() + 1;
        long time = (l + r) / 2;
        long cnt = 0; // 탑승한 아이 수
        while (l <= r) {
            long mid = (l + r) / 2;
            cnt = 0;
            for (int ride : rides) {
                cnt += mid / ride + 1; // mid 초 동안 태울 수 있는 아이 수 (처음엔 놀이기구가 비어있으니 대기시간이 없음)
            }
            if (cnt >= n) {
                // 전부 태울 수 있으면 시간 줄이기
                r = mid - 1;
                time = mid;
            } else {
                // 시간 부족하면 늘리기
                l = mid + 1;
            }
        }

        // 대기 없이 모두 탑승할 수 있으면
        if (time == 0) {
            System.out.println(n);
            System.exit(0);
        }

        // 마지막으로 탄 놀이기구는 운행시간이 time 의 약수이다
        cnt = 0; // 마지막 초 바로 전까지 탑승한 아이 수
        for (int ride : rides) {
            cnt += (time - 1) / ride + 1;
        }

        // 마지막 초에 탑승
        for (int i = 0; i < m; i++) {
            if (time % rides[i] == 0) cnt += 1;
            // 마지막 탑승자
            if (cnt == n) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}