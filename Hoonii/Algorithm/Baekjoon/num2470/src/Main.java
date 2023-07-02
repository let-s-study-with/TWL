/*
https://www.acmicpc.net/problem/2470
1초 - 128MB
N은 2 이상 100,000 이하
N개의 정수 -1,000,000,000 이상 1,000,000,000 이하 ( 산성 - 양수 , 알칼리성 - 음수 ) -> 두수합 최대 2*10^9 = int 가능

O (N log(N)) -> 최대 1,600,000
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
        ArrayList<Integer> arrayList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrayList.add(Integer.valueOf(st.nextToken()));
        }

        Collections.sort(arrayList);

        int L = 0;
        int R = N - 1;
        int sum = Integer.MAX_VALUE;
        int answerL = arrayList.get(L);
        int answerR = arrayList.get(R);

        while (true){
            if (L==R) break;

            int caseSum = arrayList.get(L) + arrayList.get(R);
            int positiveSum = Math.abs(caseSum);

            if (positiveSum < sum){
                answerR = arrayList.get(R);
                answerL = arrayList.get(L);
                sum = positiveSum;

                if (sum==0) break;
            }

            if (caseSum < 0) L++;
            else if (caseSum > 0) R--;
        }

        System.out.println(answerL + " " + answerR);
    }
}
