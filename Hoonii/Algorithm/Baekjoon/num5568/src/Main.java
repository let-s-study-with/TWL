/*
https://www.acmicpc.net/problem/5568

아이디어
백트래킹 -> 모든 경우 확인하고 HashSet 저장

자료구조
HashSet

시간복잡도
O(n^k)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] used;
    static int[] cards;
    static Set<String> answers = new HashSet<>();
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        used = new boolean[n];
        cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            backtrack(i, 1, String.valueOf(cards[i]));
        }

        System.out.println(answers.size());
    }

    public static void backtrack(int index, int useCount, String str) {
        if (useCount == k) {
            answers.add(str);
            return;
        }

        used[index] = true;

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            backtrack(i, useCount + 1, str + cards[i]);
        }

        used[index] = false;
    }
}
