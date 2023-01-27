package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2629 {
    static int n;
    static int[] weights;
    static int totalSum;
    static boolean[] possible;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 추 무게 입력
        n = Integer.parseInt(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        totalSum = Arrays.stream(weights).sum();
        possible = new boolean[totalSum + 1];

        // 구슬의 무게 확인 가능 여부 확인
        checkWeight();

        // 확인할 구슬 무게 입력
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < t; i++) {
            int w = Integer.parseInt(st.nextToken());

            if (w > totalSum || !possible[w]) {
                System.out.print("N ");
            } else {
                System.out.print("Y ");
            }
        }
    }

    static void checkWeight() {

        possible[0] = true;

        for (int i = 0; i < n; i++) {
            int weight = weights[i];
            ArrayList<Integer> tmp = new ArrayList<>();

            // 추 하나의 무게 그대로 재기
            tmp.add(weight);

            for (int j = 0; j <= totalSum; j++) {
                if (possible[j]) {

                    // 구슬 | 기존추 + weight
                    if (weight + j <= totalSum)
                        tmp.add(weight + j);

                    // 구슬 + 기존추 | weight
                    if (weight - j >= 0)
                        tmp.add(weight - j);

                    // 구슬 + weight | 기존추
                    if (j - weight >= 0)
                        tmp.add(j - weight);
                }
            }

            for (Integer t : tmp) {
                possible[t] = true;
            }

        }

    }
}
