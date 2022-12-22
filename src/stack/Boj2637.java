package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;


public class Boj2637 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String formula = bufferedReader.readLine();
        StringBuilder sb = new StringBuilder();

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < formula.length(); i++) {

            char c = formula.charAt(i);

            if (c == '+' || c == '-' || c == '*' | c == '/') {
                // 우선순위가 큰 연산자부터 계산한다
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                    sb.append(stack.pop());
                }
                stack.add(c);
            } else if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else {
                sb.append(c);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

    // 연산자별 우선순위
    static int priority(char operator) {
        if (operator == '(' || operator == ')') return 0;
        else if (operator == '+' || operator == '-') return 1;
        else return 2;
    }

}
