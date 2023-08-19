/*
https://www.acmicpc.net/problem/1326

아이디어
bfs

자료구조
Queue, 배열

시간복잡도
O(V + E)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, source, destination;
    static int[] map;
    static boolean[] visitied;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        source = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        visitied = new boolean[N + 1];

        System.out.println(bfs(source));
    }

    public static int bfs(int index) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(index, 0));
        visitied[index] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 목적지 도착
            if (node.index == destination) return node.depth;

            // 현재 노드에서 갈 수 있는 징검다리 시작지점, 끝지점 계산 후 루프
            int startIndex = node.index % map[node.index];
            if (startIndex == 0) startIndex += map[node.index];

            int endIndex = N;

            for (int adj = startIndex; adj <= endIndex; adj += map[node.index]) {
                if (visitied[adj]) continue;

                queue.add(new Node(adj, node.depth + 1));
                visitied[adj] = true;
            }
        }

        return -1;
    }
}

class Node {
    int index;
    int depth;

    public Node(int index, int depth) {
        this.index = index;
        this.depth = depth;
    }
}
