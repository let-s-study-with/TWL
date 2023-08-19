/*
https://www.acmicpc.net/problem/2178

아이디어
BFS

자료구조
배열

시간복잡도
O(V+E)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visitied;
    static int[][] position = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        visitied = new boolean[N][M];
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0, 0, 1));
        visitied[0][0] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.x == N - 1 && node.y == M - 1) return node.moveCount;

            for (int i = 0; i < 4; i++) {
                int newx = node.x + position[i][0];
                int newy = node.y + position[i][1];

                if (newx < 0 || newy < 0 || newx >= N || newy >= M) continue;
                if (visitied[newx][newy]) continue;
                if (map[newx][newy] == 0) continue;

                queue.add(new Node(newx, newy, node.moveCount + 1));
                visitied[newx][newy] = true;
            }
        }

        return -1;
    }
}

class Node {
    int x;
    int y;
    int moveCount;

    public Node(int x, int y, int moveCount) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
    }
}
