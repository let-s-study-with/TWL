/*
https://www.acmicpc.net/problem/14425

아이디어
[[ 시간초과 실패 .. 다른 사람 풀이 찾아봐도 똑같은거 같은데 시간초과 이유를 모르겠음 .. ]]
트라이 만들고 검사

자료구조
트라이

시간복잡도
O( (N+M) * 문자열 길이 ) -> 최악의 경우 10^7 (0.1초)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node root = new Node();
        while (N-- > 0) {
            String str = br.readLine();

            insertNode(root, str);
        }

        int answer = 0;
        while (M-- > 0) {
            String str = br.readLine();

            if (searchNode(root, str)) answer++;
        }

        System.out.println(answer);
    }

    public static void insertNode(Node node, String str) {
        for (char c : str.toCharArray()){
            node = node.child.computeIfAbsent(c, key -> new Node());
        }

        node.isLast = true;
    }

    public static boolean searchNode(Node node, String str) {
        for (char c : str.toCharArray()){
            node = node.child.getOrDefault(c, null);

            if (node == null) return false;
        }

        return node.isLast;
    }
}

class Node {
    boolean isLast;
    Map<Character, Node> child = new HashMap<>();
}
