import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://www.acmicpc.net/problem/1966
2초 - 128MB
테스트 케이스 수 T
문서의 개수 N (1 ≤ N ≤ 100)
출력 순번이 궁금한 문서 번호 (0 ≤ M < N )
N개 문서의 중요도

아이디어
1. Queue (순서 저장) , Priority Queue (출력 가능 여부 체크)

O(N^2) - 최악
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Paper> queue = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            int index = 0;
            while (N-- > 0) {
                int num = Integer.parseInt(st.nextToken());
                priorityQueue.add(num);
                queue.add(new Paper(index, num));
                index++;
            }

            int answer = 0;
            while (true) {
                Paper paper = queue.poll();

                if (paper.priority != priorityQueue.peek()) queue.add(paper);
                else {
                    answer++;
                    priorityQueue.poll();
                    if (paper.index == M) break;
                }
            }

            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }
}

class Paper {
    int index;
    int priority;

    Paper(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }
}
