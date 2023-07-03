/*
https://www.acmicpc.net/problem/2437
1초 - 128MB
저울추의 개수 N ( 1 <= N <= 1,000 )
각 추의 무게 w ( 1 <= w <= 1,000,000 )

아이디어
1. 추 무게 정렬
2. 오름차순으로 다움 추의 무게를 더하며 더한 값 + 1 보다 다음 추가 크면 비교 못하는 수이므로 결과 출력

O ( N log (N) )
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

        int result = 0;
        for (int i : arrayList) {
            if (result + 1 >= i) result += i;
            else break;
        }

        System.out.println(result + 1);
    }
}
