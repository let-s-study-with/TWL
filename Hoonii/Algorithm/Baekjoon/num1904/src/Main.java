/*
https://www.acmicpc.net/problem/1904
0.75 초 - 256MB
N (1 ≤ N ≤ 1,000,000)

아이디어
1. DP

O(N)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dy = new int[N + 1];
        dy[0] = 1;
        dy[1] = 1;

        for (int i = 2; i < N + 1; i++) {
            dy[i] = (dy[i - 2] + dy[i - 1]) % 15746;
        }

        System.out.println(dy[N]);
    }
}
