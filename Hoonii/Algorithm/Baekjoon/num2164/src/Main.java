/*
https://www.acmicpc.net/problem/2164
2초 - 128MB
첫째 줄에 정수 N(1 ≤ N ≤ 500,000)

O(n log(n)) - for + queue
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        boolean status = true;
        while (true) {
            if (queue.size() == 1) break;

            if (status) {
                queue.poll();
                status = false;
            } else {
                queue.add(queue.poll());
                status = true;
            }
        }

        System.out.println(queue.poll());
    }
}
