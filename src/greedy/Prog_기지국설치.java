package greedy;

public class Prog_기지국설치 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(11, new int[]{4, 11}, 1));
        System.out.println(solution.solution(16, new int[]{9}, 2));
        System.out.println(solution.solution(10, new int[]{1, 3, 4, 5, 6}, 1));
        System.out.println(solution.solution(10, new int[]{1, 4, 5, 9}, 1));
        System.out.println(solution.solution(20000, new int[]{}, 1));
    }

    static class Solution {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;

            int left = 1, right = n; // 전파가 안닿는 구간

            for (int station : stations) {

                // 전파 안닿는 곳에 기지국 설치
                if (station - w - left >= 1) {
                    answer += (station - w - left) / (2 * w + 1);
                    if ((station - w - left) % (2 * w + 1) > 0) answer += 1;
                }

                // 구간 왼쪽 끝 업데이트
                left = station + w + 1;
            }

            // 마지막 기지국 다음 구간
            if (left <= n) {
                int range = right - left + 1;
                answer += range / (2 * w + 1);
                if (range % (2 * w + 1) > 0) answer += 1;
            }

            return answer;
        }
    }


}
