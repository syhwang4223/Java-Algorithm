package simulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/77486
public class Prog_다단계칫솔판매 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10})));
        System.out.println(Arrays.toString(solution.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"sam", "emily", "jaimie", "edward"},
                new int[]{2, 3, 5, 4})));
    }
    static class Solution {
        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

            int n = enroll.length; // 조직원 수

            // 판매원을 이름 대신 번호로 접근, 관리하기 위함
            Map<String, Integer> sellerMap =new HashMap<>();
            for (int i = 0; i < n; i++) {
                sellerMap.put(enroll[i], i);
            }

            // 다단계 조직에 참여시킨 부모 판매원
            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                if (referral[i].equals("-")) parent[i] = n; // center 바로 아래 조직원
                else parent[i] = sellerMap.get(referral[i]);
            }


            int[] answer = new int[n];
            int m = seller.length;

            // 칫솔 판매 이익 계산
            for (int i = 0; i < m; i++) {
                int sellerNum = sellerMap.get(seller[i]); // 판매한 조직원
                int money = amount[i] * 100; // 수익

                // 보스 아래까지 반복
                while (sellerNum != n) {
                    int tax = (int) (money * 0.1); // 상납금

                    // 수익의 90퍼는 갖고 10퍼는 상위 조직원에게 넘긴다
                    // 만약 10퍼가 1원이 안되면 내가 다 갖는다
                    answer[sellerNum] += money - tax;

                    // 상위 조직원도 마찬가지로 90만 먹고 10은 넘겨야 한다.
                    sellerNum = parent[sellerNum];
                    money = tax;
                }
            }

            return answer;
        }
    }
}
