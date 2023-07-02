import java.io.IOException;
import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/49191
선수의 수 1 <= n <= 100
경기 결과의 수 1 <= m <= 4500

O(n * m^2) - 너무 비효율 적
 */
public class Solution {
    public int solution(int n, int[][] results) throws IOException {
        int answer = 0;

        HashSet<Integer>[] leafs = new HashSet[n + 1];
        HashSet<Integer>[] parents = new HashSet[n + 1];

        for (int i = 1; i < n + 1; i++) {
            leafs[i] = new HashSet<>();
            parents[i] = new HashSet<>();
        }

        for (int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];

            leafs[win].add(lose);
            parents[lose].add(win);
        }

        for (int i = 1; i < n + 1; i++) {
            for (int parent : parents[i]) {
                for (int leaf : leafs[i]) {
                    leafs[parent].add(leaf);
                    parents[leaf].add(parent);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 1; j < n + 1; j++) {
                if (i == leafs[j].size() && (n - 1) - i == parents[j].size()) count++;
            }

            if (count == 1) answer++;
            else if (count > 1) i += count - 1;
        }

        return answer;
    }
}
