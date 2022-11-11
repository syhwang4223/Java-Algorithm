package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj2143 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        // 입력
        int T = Integer.parseInt(bufferedReader.readLine());
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] A = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(bufferedReader.readLine());
        int[] B = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // A 의 부배열
        ArrayList<Integer> subA = subArr(n, A);
        subA.sort(Comparator.naturalOrder());
        // B 의 부배열
        ArrayList<Integer> subB = subArr(m, B);
        subB.sort(Comparator.naturalOrder());

        long answer = 0;

        // 투포인터
        int left = 0, right = subB.size() - 1;
        while (left < subA.size() && right >= 0) {
            int sum = subA.get(left) + subB.get(right);

            if (sum == T) {
                long aCount = 0, bCount = 0;
                long a = subA.get(left), b = subB.get(right);

                while (left < subA.size() && subA.get(left) == a) {
                    left += 1;
                    aCount += 1;
                }
                while (right >= 0 && subB.get(right) == b) {
                    right -= 1;
                    bCount += 1;
                }
                answer += aCount * bCount;
            }
            else if (sum < T) left += 1;
            else right -= 1;
        }

        System.out.println(answer);



    }

    // 부배열
    private static ArrayList<Integer> subArr(int k, int[] arr) {
        ArrayList<Integer> sub = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int sum = arr[i];
            sub.add(sum);
            for (int j = i + 1; j < k; j++) {
                sum += arr[j];
                sub.add(sum);
            }
        }
        return sub;
    }
}
