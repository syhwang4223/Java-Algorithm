import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        // 보석 정보 입력
        int[][] jewels = new int[n][2];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            jewels[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            jewels[i][1] = Integer.parseInt(stringTokenizer.nextToken());
        }

        // 가방 정보 입력
        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(bufferedReader.readLine());
        }
        // 가방 무게 오름차순 정렬
        Arrays.sort(bags);

        // 보석 무게 오름차순, 무게 같으면 가격 내림차순 정렬
        Arrays.sort(jewels, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.reverseOrder());
        long answer = 0;

        // 모든 가방에 대해 반복문 수행
        // 담을 수 있는 보석 중 가장 비싼걸 넣으면 된다.
        int idx = 0;
        for (int bag : bags) {
            while (idx < n && jewels[idx][0] <= bag) {
                // 해당 가방에 담을 수 있는 보석은 일단 전부 넣는다. (가격 내림차순으로 정렬하면서)
                queue.add(jewels[idx++][1]);
            }
            // 담은 보석 중 가장 비싼 보석 하나만 챙긴다
            if (!queue.isEmpty()) {
                answer += queue.poll();
            }
        }

        System.out.println(answer);
    }
}
