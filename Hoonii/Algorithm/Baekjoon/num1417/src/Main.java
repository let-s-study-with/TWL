/*
https://www.acmicpc.net/problem/1417

아이디어
기능 구현

자료구조
내림차순 PQ

시간복잡도
O(N log N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int dasom = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N - 1; i++) {
            pq.add(Integer.valueOf(br.readLine()));
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            int biggest = pq.poll();

            if (dasom > biggest) break;
            else {
                pq.add(--biggest);
                dasom++;

                answer++;
            }
        }

        System.out.println(answer);
    }
}
