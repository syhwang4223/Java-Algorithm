package simulation;

// https://school.programmers.co.kr/learn/courses/30/lessons/152995
public class Prog_인사고과 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{{2,2},{1,4},{3,2},{3,2},{2,1}}));
        System.out.println(solution.solution(new int[][]{{4,1},{2,4},{3,5}}));
        System.out.println(solution.solution(new int[][]{{1,1},{1,1},{1,1}}));
    }

    static class Solution {
        public int solution(int[][] scores) {

            int wanho = scores[0][0] + scores[0][1]; // 완호 점수합
            int rank = 1; // 원호 등수

            for (int i = 1; i < scores.length; i++) {

                int scoreSum = scores[i][0] + scores[i][1];

                // 완호보다 점수합 낮거나 같은 애들은 신경 쓸 필요 없다
                if (scoreSum <= wanho) continue;

                // 완호보다 두 점수 모두 높은 사원이 있으면 완호는 인센티브를 못받는다
                if (scores[0][0] < scores[i][0] && scores[0][1] < scores[i][1]) return -1;

                // 자기보다 두 점수가 모두 높은 직원이 있으면 인센티브 대상에서 제외된다
                boolean insen = true;
                for (int[] score : scores) {
                    if (scores[i][0] < score[0] && scores[i][1] < score[1]) {
                        insen = false;
                        break;
                    }
                }
                if (insen) rank += 1;
            }

            return rank;
        }
    }
}

