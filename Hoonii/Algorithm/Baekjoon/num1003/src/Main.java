/*
https://www.acmicpc.net/problem/1003

아이디어
기존 계산된 피보나치 저장 및 활용

자료구조
HashMap, 배열

시간복잡도
O(N) -> 각 테스트 케이스 중 가장 큰 수
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<Integer, int[]> dy = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        dy.put(0, new int[]{1, 0});
        dy.put(1, new int[]{0, 1});

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            Arrays.stream(recursion(Integer.parseInt(br.readLine()))).forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int[] recursion(int n) {
        if (dy.containsKey(n)) return dy.get(n);

        int[] num1 = recursion(n - 1);
        int[] num2 = recursion(n - 2);

        dy.put(n, new int[]{num1[0] + num2[0], num1[1] + num2[1]});
        return dy.get(n);
    }
}
