/*
https://www.acmicpc.net/problem/14426

아이디어
트라이 만들고 자리수 전체 일치하는 개수 파악

자료구조
트라이

시간복잡도
O( M * 500 ) -> 최악의 경우 5 * 10^6
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node root = new Node();

        while (N-- > 0) {
            insertString(root, br.readLine());
        }

        int answer = 0;
        while (M-- > 0) {
            if (searchString(root, br.readLine())) answer++;
        }

        System.out.println(answer);
    }

    public static void insertString(Node root, String str) {
        for (char c : str.toCharArray()) {
            root = root.map.computeIfAbsent(c, key -> new Node());
        }
    }

    public static boolean searchString(Node root, String str) {
        for (char c : str.toCharArray()) {
            root = root.map.getOrDefault(c, null);

            if (Objects.isNull(root)) return false;
        }
        return true;
    }
}

class Node {
    boolean isLast;
    Map<Character, Node> map = new HashMap<>();

}