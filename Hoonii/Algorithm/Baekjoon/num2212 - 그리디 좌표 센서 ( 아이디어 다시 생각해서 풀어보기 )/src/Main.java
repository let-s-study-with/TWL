/*
https://www.acmicpc.net/problem/2212
2초 - 128MB
센서의 개수 N(1 ≤ N ≤ 10,000)
집중국의 개수 K(1 ≤ K ≤ 1000)
센서의 좌표가 한 개의 정수로 N개, 각 좌표의 절댓값은 1,000,000 이하

아이디어
그리디
1. 센서 좌표 목록 오름 차순 정렬
2. 센서 간 거리 저장 후 정렬
3. N - K 수 만큼 거리 목록에서 작은것 부터 빼서 answer 더함

O(N)
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> sensorList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sensorList.add(Integer.valueOf(st.nextToken()));
        }
        Collections.sort(sensorList);

        if (K >= N){
            System.out.println(0);
            return;
        }

        ArrayList<Integer> distanceList = new ArrayList<>();
        int before = sensorList.get(0);
        for (int i = 1; i < N; i++) {
            distanceList.add(sensorList.get(i) - before);
            before = sensorList.get(i);
        }
        Collections.sort(distanceList);

        int answer = 0;
        for (int i = 0; i < N - K; i++) {
            answer += distanceList.get(i);
        }

        System.out.println(answer);
    }
}
