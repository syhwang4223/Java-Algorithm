package greedy;

import java.util.ArrayList;
import java.util.Collections;

public class Prog_단속카메라 {

    // 단속카메라
    public static void main(String[] args) {
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        Solution solution = new Solution();
        System.out.println(solution.solution(routes));
    }

    static class Solution {
        public int solution(int[][] routes) {

            int answer = 0;

            ArrayList<Car> cars =  new ArrayList<>();
            for (int[] route : routes) {
                cars.add(new Car(route[0], route[1]));
            }
            // 빨리 나가는 순으로 정렬
            Collections.sort(cars);

            int cam = Integer.MIN_VALUE;
            for (Car car : cars) {
                if (cam < car.in) {
                    // 현재 카메라의 위치가 차량이 들어오기 전이면 새로운 카메라 설치
                    cam = car.out;
                    answer += 1;
                }
            }

            return answer;
        }

        static class Car implements Comparable<Car>{
            int in, out;

            public Car(int in, int out) {
                this.in = in;
                this.out = out;
            }

            @Override
            public int compareTo(Car o) {
                return this.out - o.out;
            }
        }
    }

}