/*
https://www.acmicpc.net/problem/1991

아이디어
전위 , 중위 , 후위 재귀

자료구조
HashMap

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
    static Map<Character, ArrayList<Character>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char parent = st.nextToken().charAt(0);
            for (int i = 0; i < 2; i++) {
                map.computeIfAbsent(parent, key -> new ArrayList<>()).add(st.nextToken().charAt(0));
            }
        }

        sb.append(preOrder('A', "")).append("\n");
        sb.append(inOrder('A', "")).append("\n");
        sb.append(postOrder('A', "")).append("\n");

        System.out.println(sb);
    }

    public static String preOrder(char node, String str) {
        str += node;
        if (map.get(node).get(0) != '.') str = preOrder(map.get(node).get(0), str);
        if (map.get(node).get(1) != '.') str = preOrder(map.get(node).get(1), str);

        return str;
    }

    public static String inOrder(char node, String str) {
        if (map.get(node).get(0) != '.') str = inOrder(map.get(node).get(0), str);
        str += node;
        if (map.get(node).get(1) != '.') str = inOrder(map.get(node).get(1), str);

        return str;
    }

    public static String postOrder(char node, String str) {
        if (map.get(node).get(0) != '.') str = postOrder(map.get(node).get(0), str);
        if (map.get(node).get(1) != '.') str = postOrder(map.get(node).get(1), str);
        str += node;

        return str;
    }
}
