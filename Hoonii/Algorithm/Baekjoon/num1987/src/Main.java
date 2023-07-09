/*
https://www.acmicpc.net/problem/1987
2초 - 256MB

아이디어
1. DFS + 백트래킹

O(R*C)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static HashSet<Character> hashSet = new HashSet<>();
    static int R;
    static int C;
    static char[][] map;
    static int[][] pos = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(dfs(0, 0, 1));
    }

    public static int dfs(int x, int y, int count) {
        hashSet.add(map[x][y]);

        int myCount = count;

        for (int i = 0; i < 4; i++) {
            int newx = x + pos[i][0];
            int newy = y + pos[i][1];

            if (newx < 0 || newy < 0 || newx >= R || newy >= C) continue;
            if (hashSet.contains(map[newx][newy])) continue;

            count = Math.max(count, dfs(newx, newy, myCount+1));
        }

        hashSet.remove(map[x][y]);

        return count;
    }
}
