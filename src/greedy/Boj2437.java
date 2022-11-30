package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2437 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] weights = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(weights);

        int sum = 0;

        for (int weight : weights) {
            if (weight > sum + 1) {
                break;
            } else {
                sum += weight;
            }
        }
        System.out.println(sum + 1);
    }
}
