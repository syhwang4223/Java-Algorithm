package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1715 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) queue.add(Integer.valueOf(bufferedReader.readLine()));

        // 어차피 덧셈은 n - 1 번 해야하므로 매번 가장 작은 두 수를 더해주면 된다

        int answer = 0;
        while (n-- > 1) {
            int card1 = queue.poll();
            int card2 = queue.poll();
            answer += card1 + card2;
            queue.add(card1 + card2);
        }

        System.out.println(answer);

    }
}
