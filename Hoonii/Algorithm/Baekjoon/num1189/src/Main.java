/*
https://www.acmicpc.net/problem/1189

아이디어
백트래킹 + dfs

자료구조
2차원 배열

시간복잡도
O(4^(R * C)) -> 최악의 경우고, 중간 K 값을 초과하면 return 하기에 많이 줄어듬
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, K, answer;
    static boolean[][] visited;
    static int[][] position = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();

            for (int j = 0; j < C; j++) {
                if (str.charAt(j) == 'T') visited[i][j] = true;
            }
        }

        dfs(R - 1, 0, 1);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int moveCount) {
        if (moveCount > K) return;

        if (x == 0 && y == (C - 1)) {
            if (moveCount == K) answer++;
            return;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int newx = x + position[i][0];
            int newy = y + position[i][1];

            if (newx < 0 || newy < 0 || newx >= R || newy >= C) continue;
            if (visited[newx][newy]) continue;

            dfs(newx, newy, moveCount + 1);
        }

        visited[x][y] = false;
    }
}
