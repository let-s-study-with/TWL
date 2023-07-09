/*
https://www.acmicpc.net/problem/9663
10초 - 128MB
N이 주어진다. (1 ≤ N < 15)

아이디어
1. 멥 백트래킹 - DFS

O (N^3)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] check;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        check = new int[N][N];

        int answer = dfs(0);

        System.out.println(answer);
    }

    public static int dfs(int count) {
        if (count == N){
            return 1;
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (check[count][i] > 0) continue;

            // 대각선 밑 세로 체크
            int k = 0;
            for (int j = count; j < N; j++) {
                int plusK = i + k;
                int minusK = i - k;

                check[j][i]++;
                if (plusK < N) check[j][plusK]++;
                if (minusK >= 0) check[j][minusK]++;
                k++;
            }

            answer += dfs(count + 1);

            // 대각선 및 세로 체크 해제
            k--;
            for (int j = N-1; j >= count; j--) {
                int plusK = i + k;
                int minusK = i - k;

                check[j][i]--;
                if (plusK < N) check[j][plusK]--;
                if (minusK >= 0) check[j][minusK]--;
                k--;
            }
        }

        return answer;
    }
}
