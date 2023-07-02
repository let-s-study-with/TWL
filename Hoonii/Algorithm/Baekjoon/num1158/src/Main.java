/*
https://www.acmicpc.net/problem/1158
2초 - 256MB
사람의 수 N , 제거 되는 순서 K ( 1 <= K <= N <= 5,000 )

O(K * N) - 최악 2.5*10^7 0.25초
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            queue.add(i);
        }

        sb.append("<");
        int count = 0;
        while (queue.size() != 1) {
            int num = queue.poll();

            count++;
            if (count == K) {
                count = 0;
                sb.append(num).append(", ");
                continue;
            }

            queue.add(num);
        }

        sb.append(queue.poll()).append(">");
        System.out.println(sb);
    }
}
