package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9935
public class Boj9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] origin = br.readLine().toCharArray(); // 원본 문자열
        String boom = br.readLine(); // 폭발 문자열

        StringBuilder result = new StringBuilder(); // 폭발하고 남은 문자열

        for (char c : origin) {

            // 문자 추가
            result.append(c);

            // 폭발 문자열이 만들어지면 제거
            // (제거하고 남은 문자열이 다시 폭발 문자열을 포함할 수 있으므로 반복)
            while (result.length() >= boom.length()
                    && result.substring(result.length() - boom.length(), result.length()).equals(boom)) {
                result.replace(result.length() - boom.length(), result.length(), "");
            }
        }

        System.out.println(result.length() == 0 ? "FRULA" : result);
    }
}
