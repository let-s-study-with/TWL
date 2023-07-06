/*
https://www.acmicpc.net/problem/1781
2초 - 256MB

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] problems = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            problems[i][0] = num;
            problems[i][1] = count;
        }

        Arrays.sort(problems, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int num = problems[i][0];
            int count = problems[i][1];

            if (priorityQueue.size() < num) {
                priorityQueue.add(count);
            } else {
                priorityQueue.add(Math.max(priorityQueue.poll(), count));
            }
        }

        int answer = 0;
        while (!priorityQueue.isEmpty()){
            answer += priorityQueue.poll();
        }

        System.out.println(answer);
    }
}
