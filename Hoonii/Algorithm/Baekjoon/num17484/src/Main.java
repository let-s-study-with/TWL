/*
https://www.acmicpc.net/problem/17484
1초 - 256MB
2 <= N,M <= 6

O ( (N*M)^2 ) 보단 작음 - DFS 최악의 경우 시간복잡도
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] pos = new int[][]{{1, -1}, {1, 0}, {1, 1}};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // map 저장
        int min = Integer.MAX_VALUE; // 모든 DFS 경우 최소 비용 저장

        // map 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 시작점 경우 DFS 시작 및 비용 비교
        for (int i = 0; i < M; i++) {
            min = Math.min(min, dfs(0,i, -1));
        }

        System.out.println(min);
    }

    public static int dfs(int x, int y, int before) {
        // 마지막 줄 도착 시 비용 return
        if (x == N - 1) return map[x][y];

        // DFS 경우의 수 중 최소 값 저장
        int count = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            // 동일 경로 경우 제거
            if (i == before) continue;

            int newx = x + pos[i][0];
            int newy = y + pos[i][1];

            // map 범위 벗어난 경우 제거
            if (newx < 0 || newy < 0 || newx >= N || newy >= M) continue;

            count = Math.min(count, dfs(newx, newy, i));
        }

        return count + map[x][y];
    }
}
