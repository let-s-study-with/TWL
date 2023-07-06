/*
https://www.acmicpc.net/problem/1092
2초 - 128MB
크레인의 수 N ( 1 <= N <= 50 )
각 크레인 무게 제한 L ( 1 <= L <= 1,000,000 )
박스의 수 M ( 1 <= M <= 10,000 )
박스의 무게 W ( 1 <= W <= 1,000,000 )

아이디어
그리디
1. 박스 / 크레인 내림차순 정렬
2. 모든 크레인 박스 체크 ( 옮긴 박스는 체크 )

O (N * M) -> 5*10 * 10^4 -> 5*10^5 ( 최악의 경우 )
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> cranes = new ArrayList<>();
        ArrayList<Integer> boxs = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes.add(Integer.valueOf(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        boolean[] checkBox = new boolean[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxs.add(Integer.valueOf(st.nextToken()));
        }

        Collections.sort(cranes, Collections.reverseOrder());
        Collections.sort(boxs, Collections.reverseOrder());

        if (cranes.get(0) < boxs.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        int done = 0;
        time:
        while (done != M) {
            time++;
            crane:
            for (int crane : cranes) {
                int boxIndex = -1;
                for (int box : boxs) {
                    boxIndex++;
                    if (checkBox[boxIndex]) continue;

                    if (crane >= box) {
                        done++;
                        checkBox[boxIndex] = true;
                        continue crane;
                    }
                }
                continue time;
            }
        }

        System.out.println(time);
    }
}
