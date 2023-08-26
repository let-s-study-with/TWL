/*
https://www.acmicpc.net/problem/2637

아이디어
위상 정렬 + 재귀

자료구조
HashMap

시간복잡도
O(N + M * M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main3 {
    static Map<Integer, Integer> answer = new TreeMap<>();
    static Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int result = Integer.parseInt(st.nextToken());
            int requireNum = Integer.parseInt(st.nextToken());
            int requireAmount = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(result, key -> new HashMap<>()).put(requireNum, requireAmount);
        }

        recursion(N, 1);

        for (Map.Entry<Integer, Integer> entry : answer.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        System.out.println(sb);
    }

    public static void recursion(int N, int amount) {
        if (!map.containsKey(N)) {
            answer.put(N, answer.getOrDefault(N, 0) + amount);
            return;
        }

        for (Map.Entry<Integer, Integer> entry : map.get(N).entrySet()) {
            recursion(entry.getKey(), entry.getValue() * amount);
        }
    }
}
