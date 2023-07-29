/*
https://www.acmicpc.net/problem/14725

아이디어
TreeMap 에 각 노드 정보 저장 후 출력

자료구조
TreeMap

시간복잡도
O(N * K)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            List<String> list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                list.add(st.nextToken());
            }

            insertNode(root, list);
        }

        outputNode(root, "");

        System.out.println(sb);
    }

    public static void insertNode(Node root, List<String> list) {
        if (list.size() == 0) return;

        root = root.map.computeIfAbsent(list.get(0), key -> new Node());
        list.remove(0);

        insertNode(root, list);
    }

    public static void outputNode(Node root, String depth) {
        for (Map.Entry<String, Node> entry : root.map.entrySet()) {
            sb.append(depth).append(entry.getKey()).append("\n");

            outputNode(entry.getValue(), depth+"--");
        }
    }
}

class Node {
    Map<String, Node> map = new TreeMap<>();
}