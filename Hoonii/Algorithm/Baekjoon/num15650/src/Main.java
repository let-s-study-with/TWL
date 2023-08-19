/*
https://www.acmicpc.net/problem/15650

아이디어
백트래킹 -> 모든 경우 계산

자료구조
변수

시간복잡도
O(N^M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            backtrack(i, 1, i + " ");
        }

        System.out.println(sb);
    }

    public static void backtrack(int num, int useCount, String str) {
        if (useCount == M) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = num + 1; i <= N; i++) {
            backtrack(i, useCount + 1, str + i + " ");
        }
    }
}
