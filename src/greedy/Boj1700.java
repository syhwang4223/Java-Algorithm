package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Boj1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        // 사용할 전기용품들
        List<Integer> items = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        // 각 전기용품의 사용 횟수
        int cnt[] = new int[items.stream().max(Comparator.naturalOrder()).get() + 1];
        for (int item : items) {
            cnt[item] += 1;
        }

        // 멀티탭에 꽂혀있는 플러그들
        Set<Integer> plugs = new HashSet<Integer>();

        int answer = 0;
        while (!items.isEmpty()) {

            int item = items.get(0);
            items.remove(0);
            cnt[item] -= 1;

            if (plugs.size() < n) {

                // 처음 n 개 까지는 플러그를 뽑을 필요가 없다
                plugs.add(item);

            } else if (plugs.contains(item)) {

                // 이미 멀티탭에 꽂혀 있는 플러그여도 뽑을 필요가 없다

            } else {

                // 그 외에는 어느 플러그를 뽑을 것인가?

                int idx = 0;
                int remove = 0; // 제거할 플러그
                for (Integer plug : plugs) {

                    // 앞으로 아예 사용하지 않는 전기용품이 있으면 그걸 뽑는다
                    if (cnt[plug] == 0) {
                        remove = plug;
                        break;
                    }

                    // 그렇지 않으면? 제일 마지막에 사용할 전기용품을 뽑는다
                    else {
                        idx = Math.max(idx, items.indexOf(plug));
                        remove = items.get(idx);
                    }
                }

                // 해당 플러그 뽑기
                plugs.remove(remove);
                answer += 1;

                // 사용할 플러그 꽂기
                plugs.add(item);
            }

        }

        System.out.println(answer);

    }
}
