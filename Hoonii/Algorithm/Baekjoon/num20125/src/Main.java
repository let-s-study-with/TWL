/*
https://www.acmicpc.net/problem/20125
1초 - 1024MB
5 ≤ N ≤ 1,000. N은 판의 한 변의 길이
최대 -> 1000 * 1000 = 1,000,000

O(n^2)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] maps;
    static int[][] pos = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String line = st.nextToken();

            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '*') maps[i][j] = 1;
                else maps[i][j] = 0;
            }
        }

        int status = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 각 부위 계산 계산
                if (status == 1 && maps[i][j] == 1) {
                    sb.append(dfs(i, j, 0)).append(" ");
                }

                // 심장 계산
                if (status == 0 && maps[i][j] == 1) {
                    maps[i + 1][j] = 0;
                    sb.append(i + 2).append(" ").append(j + 1).append("\n");
                    status = 1;
                }
            }
        }

        System.out.println(sb);
    }

    public static int dfs(int x, int y, int count) {
        for (int i = 0 ; i < 4 ; i++ ){
            int newx = x + pos[i][0];
            int newy = y + pos[i][1];

            if (newx >= N || newy >= N || newx < 0 || newy < 0) continue;
            if (maps[newx][newy] == 0) continue;

            maps[x][y] = 0;
            return dfs(newx, newy, count+1);
        }

        maps[x][y] = 0;
        return count+1;
    }
}
