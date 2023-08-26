/*
https://www.acmicpc.net/problem/10800

아이디어
1. 전체 공을 크기 내림차순으로 정렬
2. 색 별로 전체 크기 저장
3. 사이즈 별 색 목록 저장
4. 전체 공의 크기 합 저장
-> 가장 큰 공부터 정답 크기 계산 ( 4 - 2(같은 색 제외) - 3(같은 크기지만 다른 색) )
--> 계산 끝나면 2,3,4 업데이트

자료구조
ArrayList, HashMap

시간복잡도
O(N * log N) + O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int totalSize = 0;
        List<Ball> list = new ArrayList<>();
        Map<Integer, Integer> colorTotalSize = new HashMap<>();
        Map<Integer, ArrayList<Integer>> sizeColorList = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            list.add(new Ball(i, color, size));

            totalSize += size;
            colorTotalSize.put(color, colorTotalSize.getOrDefault(color, 0) + size);
            sizeColorList.computeIfAbsent(size, key -> new ArrayList<>()).add(color);
        }

        Collections.sort(list);

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            Ball ball = list.get(i);

            int sameCount = 0;
            for (int color : sizeColorList.get(ball.size)) {
                if (ball.color != color) sameCount++;
            }

            answer[ball.index] = totalSize - colorTotalSize.get(ball.color) - sameCount * ball.size;

            totalSize -= ball.size;
            colorTotalSize.put(ball.color, colorTotalSize.get(ball.color)- ball.size);
            sizeColorList.get(ball.size).remove((Integer) ball.color);
        }

        Arrays.stream(answer).forEach(System.out::println);
    }
}

class Ball implements Comparable<Ball> {
    int index;
    int color;
    int size;

    public Ball(int index, int color, int size) {
        this.index = index;
        this.color = color;
        this.size = size;
    }

    @Override
    public int compareTo(Ball o) {
        return o.size - this.size;
    }
}
