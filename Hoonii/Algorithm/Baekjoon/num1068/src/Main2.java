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

public class Main2 {
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
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent != -1) {
                adj[parent].add(i); // 자식 노드 목록 저장
                parents[i] = parent; // 부모 노드 번호 저장
            }
        }

        st = new StringTokenizer(br.readLine());
        int removeNode = Integer.parseInt(st.nextToken());
        adj[parents[removeNode]].remove((Integer) removeNode);
        removeNode(removeNode);

        System.out.println(countLeaf());
    }

    public static void removeNode(int removeNode) {
        // 삭제 노드 및 그의 자식 노드는 모두 트리에서 제거하는 노드 의미 -2 로 저장
        parents[removeNode] = -2;
        for (int i : adj[removeNode]) {
            removeNode(i);
        }
    }

    public static int countLeaf() {
        // 트리에서 제거된 -2 가 아니거나 자식 노드의 수가 0 이면 Leaf 이므로 카운트
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (parents[i] != -2 && adj[i].size() == 0) count++;
        }

        return count;
    }
}
