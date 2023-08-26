/*
https://www.acmicpc.net/problem/10800

아이디어
모든 공 크기 기준으로 정렬
 -> 가장 작은 공부터 색 별 크기 총합에 더함
  -> 점점 커지므로 사이즈가 같지만 않으면 다른 크기들의 총합이 정답
  !! Color 종류가 N 개라는걸 늦게봤다.. (N ^ N 은 무조건 시간초과)

자료구조
ArrayList

시간복잡도
O(N * 2000 + N log N) -> N * 2000 최악이 2 * 10^6 * 2 * 10^3 -> 4 * 10^9 -> 4초.. 시간초과 예상
-> 색 종류가 N 개.. O ( N ^ N ) 은 무조건 시간 초과
--> 로직을 바꿔야 한다..
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Ball> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            list.add(new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        int[] answer = new int[N];
        int[] colorSize = new int[N + 1];
        int[] sizeSameCount = new int[N + 1];
        int beforeSize = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            Ball ball = list.get(i);

            if (beforeSize != ball.size) {
                for (int j = 1; j < N + 1; j++) {
                    colorSize[j] += sizeSameCount[j] * beforeSize;
                }
                sizeSameCount = new int[N + 1];
            }

            sizeSameCount[ball.color]++;

            int answerSize = 0;
            for (int j = 1; j < N + 1; j++) {
                if (j == ball.color) continue;

                answerSize += colorSize[j];
            }
            answer[ball.index] = answerSize;

            beforeSize = ball.size;
        }

        Arrays.stream(answer).forEach(System.out::println);
    }
}

//class Ball implements Comparable<Ball> {
//    int index;
//    int color;
//    int size;
//
//    public Ball(int index, int color, int size) {
//        this.index = index;
//        this.color = color;
//        this.size = size;
//    }
//
//    @Override
//    public int compareTo(Ball o) {
//        return o.size - this.size;
//    }
//}
