/*
https://www.acmicpc.net/problem/9663
10초 - 128MB
N이 주어진다. (1 ≤ N < 15)



 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] check;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        check = new boolean[N][N];

        int answer = dfs(0);

        System.out.println(answer);
    }

    public static int dfs(int count) {
        if (count == N){
            return 1;
        }

        int answer = 0;

        row:
        for (int i = 0; i < N; i++) {
            if (check[count][i]) continue;

            int k = 0;
            for (int j = count; j < N; j++) {
                int plusK = i + k;
                int minusK = i - k;

                check[j][i] = true;
                if (plusK < N) check[j][plusK] = true;
                if (minusK >= 0) check[j][minusK] = true;
                k++;
            }

            answer += dfs(count + 1);

            k--;
            for (int j = N-1; j >= count; j--) {
                int plusK = i + k;
                int minusK = i - k;

                check[j][i] = false;
                if (plusK < N) check[j][plusK] = false;
                if (minusK >= 0) check[j][minusK] = false;
                k--;
            }
        }

        return answer;
    }
}
