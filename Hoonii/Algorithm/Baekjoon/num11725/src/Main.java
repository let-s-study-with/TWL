/*
https://www.acmicpc.net/problem/11725

아이디어
트리 Map 생성 후 1 루트에서부터 부모 노드 계산 후 출력

자료구조
HashMap
배열

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    static boolean[] isVisited;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        isVisited = new boolean[N + 1];
        parents = new int[N + 1];

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(num1, key -> new ArrayList<Integer>()).add(num2);
            map.computeIfAbsent(num2, key -> new ArrayList<Integer>()).add(num1);
        }

        checkParent(1, 0);

        for (int i = 2; i < N + 1; i++) {
            sb.append(parents[i]).append("\n");
        }

        System.out.println(sb);
    }

    public static void checkParent(int node, int parent) {
        isVisited[node] = true;

        parents[node] = parent;

        for (int num : map.get(node)) {
            if (isVisited[num]) continue;
            if (num == parent) continue;

            checkParent(num, node);
        }
    }
}
