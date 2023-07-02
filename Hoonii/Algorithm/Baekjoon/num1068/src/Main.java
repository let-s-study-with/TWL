/*
https://www.acmicpc.net/problem/1068
2초 - 128MB
노드의 개수 N은 50보다 작거나 같은 자연수
0번 노드부터 N-1번 노드까지, 각 노드의 부모

O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] adj;
    static int[] parents;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        parents = new int[N];
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList();
        }

        st = new StringTokenizer(br.readLine());
        int root = 0;
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());

            parents[i] = parent;
            if (parent != -1) {
                adj[parent].add(i); // 자식 노드 목록 저장
            } else {
                root = i;
            }
        }

        st = new StringTokenizer(br.readLine());
        int removeNode = Integer.parseInt(st.nextToken());

        // removeNode 를 tree 에서 제거
        if (removeNode != root) adj[parents[removeNode]].remove((Integer) removeNode);
        adj[removeNode].clear();
        parents[removeNode] = -2;

        System.out.println(countLeaf(root));
    }

    public static int countLeaf(int root) {
        // Subtree 를 활용한 dfs leaf 개수
        if (parents[root] != -2 && adj[root].size() == 0) return 1;

        int count = 0;

        for (int i : adj[root]) {
            count += countLeaf(i);
        }

        return count;
    }
}
