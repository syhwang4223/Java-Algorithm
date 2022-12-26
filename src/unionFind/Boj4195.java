package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj4195 {

    static int t;
    static BufferedReader bufferedReader;
    static Map<String, String> parent;
    static Map<String, Integer> cnt;

    public static void main(String[] args) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bufferedReader.readLine());

        // 테스트 t 번 반복
        for (int i = 0; i < t; i++) {
            testcase();
        }

    }

    static void testcase() throws IOException {

        init();
        int f = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < f; i++) {
            String[] freinds = bufferedReader.readLine().split(" ");

            for (String freind : freinds) {
                if (!parent.containsKey(freind)) parent.put(freind, freind); // 자기 자신이 부모
                if (!cnt.containsKey(freind)) cnt.put(freind, 1); // 그룹에 속한 사람 수

            }
            union(freinds[0], freinds[1]);
            System.out.println(cnt.get(find(freinds[0])));
        }
    }

    static String find(String a) {
        if (parent.get(a).equals(a)) {
            return a;
        } else {
            parent.put(a, find(parent.get(a)));
            return parent.get(a);
        }
    }

    static void union(String a, String b) {
        a = find(a);
        b = find(b);
        if (!a.equals(b)) {
            parent.put(b, a); // 부모 갱신
            int tmp = cnt.get(a) + cnt.get(b);
            cnt.put(a, tmp); // 그룹에 속한 사람 수 갱신
        }
    }

    static void init() {
        parent = new HashMap<String, String>();
        cnt = new HashMap<String, Integer>();
    }
}
