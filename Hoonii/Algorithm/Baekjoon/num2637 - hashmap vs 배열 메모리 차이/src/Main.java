/*
https://www.acmicpc.net/problem/2637

아이디어
모든 부품의 필요 수량 배열에 저장
-> 기본 부품만 출력

자료구조
배열, ArrayList

시간복잡도
O(N + M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] isPart = new boolean[N + 1];
        int[] requireAmount = new int[N + 1];
        int[] connectionCount = new int[N + 1];
        ArrayList<Part>[] lists = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int index = Integer.parseInt(st.nextToken());
            int partIndex = Integer.parseInt(st.nextToken());
            int partAmount = Integer.parseInt(st.nextToken());

            lists[index].add(new Part(partIndex, partAmount));
            connectionCount[partIndex]++;
            isPart[index] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        requireAmount[N] = 1;

        while (!queue.isEmpty()) {
            int index = queue.poll();

            for (Part part : lists[index]) {
                requireAmount[part.index] += part.amount * requireAmount[index];
                if (--connectionCount[part.index] == 0) queue.add(part.index);

            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (!isPart[i]) System.out.println(i + " " + requireAmount[i]);
        }
    }
}

class Part {
    int index;
    int amount;

    public Part(int index, int amount) {
        this.index = index;
        this.amount = amount;
    }
}
