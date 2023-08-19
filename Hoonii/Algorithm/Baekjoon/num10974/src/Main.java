/*
https://www.acmicpc.net/problem/10974

아이디어
백트래킹 -> 순차적으로 모든 경우 계산

자료구조
배열

시간복잡도
O(n!)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        used = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            backtrack(i, 1, i + " ");
        }

        System.out.println(sb);
    }

    public static void backtrack(int index, int useCount, String str) {
        if (useCount == n) {
            sb.append(str).append("\n");
            return;
        }

        used[index] = true;

        for (int i = 1; i <= n; i++) {
            if (used[i]) continue;

            backtrack(i, useCount + 1, str + i + " ");
        }

        used[index] = false;
    }
}
