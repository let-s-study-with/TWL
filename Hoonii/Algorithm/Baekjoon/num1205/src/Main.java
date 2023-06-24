/*
https://www.acmicpc.net/problem/1205
2초 - 128MB

O(nlog(n)) - Priority Queue + for문
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int caseNum = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        if (caseNum > 0 ) st = new StringTokenizer(br.readLine());
        while (caseNum-- > 0) {
            priorityQueue.add(Integer.valueOf(st.nextToken()));
        }

        int grade = priorityQueue.size() + 1;
        while (!priorityQueue.isEmpty()) {
            int caseN = priorityQueue.poll();

            // 등수 범위를 벗어난 경우
            if (priorityQueue.size() + 1 == size) {
                if (caseN >= N){
                    System.out.println(-1);
                    return;
                }
            }

            // 등수 안에 포함된 경우
            if (caseN <= N) grade--;
            else break;
        }

        System.out.println(grade);
    }
}
