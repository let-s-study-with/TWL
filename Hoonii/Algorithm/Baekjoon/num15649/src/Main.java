/*
https://www.acmicpc.net/problem/15649

아이디어
백트래킹 -> 모든 경우 순차 계산

자료구조
배열

시간복잡도
O(N^M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        used = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            backtrack(i, 1, i + " ");
        }

        System.out.println(sb);
    }

    public static void backtrack(int index, int useCount, String str) {
        if (useCount == M) {
            sb.append(str).append("\n");
            return;
        }

        used[index] = true;

        for (int i = 1; i < N + 1; i++) {
            if (used[i]) continue;

            backtrack(i, useCount + 1, str + i + " ");
        }

        used[index] = false;
    }
}
