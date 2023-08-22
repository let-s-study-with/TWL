/*
https://www.acmicpc.net/problem/2667

아이디어
dfs

자료구조
행렬

시간복잡도
O(N^2 + V+E)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][] position = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;

                answer.add(dfs(i, j, 1));
            }
        }

        Collections.sort(answer);

        System.out.println(answer.size());
        answer.forEach(System.out::println);
    }

    static int dfs(int x, int y, int moveCount) {
        map[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int newx = x + position[i][0];
            int newy = y + position[i][1];

            if (newx < 0 || newy < 0 || newx >= N || newy >= N) continue;
            if (map[newx][newy] == 0) continue;

            moveCount = Math.max(moveCount, dfs(newx, newy, moveCount + 1));
        }

        return moveCount;
    }
}
