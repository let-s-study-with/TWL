/*
https://www.acmicpc.net/problem/5052

아이디어
트라이 만들고 입력 받을 떄 저장하면서 중복 여부 검사

자료구조
트라이

시간복잡도
O(N * 각 길이)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            boolean answer = true;
            int N = Integer.parseInt(br.readLine());

            Node root = new Node();
            while (N-- > 0) {
                String str = br.readLine();

                if (!insertAndSearchNode(root, str)) {
                    answer = false;
                }
            }
            if (answer) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }

    public static boolean insertAndSearchNode(Node node, String str) {
        for (char c : str.toCharArray()) {
            int num = Integer.parseInt(String.valueOf(c));

            node = node.hashMap.computeIfAbsent(num, key -> new Node());
            if (node.lastNode) return false;
        }

        node.lastNode = true;
        return node.hashMap.size() == 0;
    }
}

class Node {
    boolean lastNode;
    HashMap<Integer, Node> hashMap = new HashMap<>();
}
