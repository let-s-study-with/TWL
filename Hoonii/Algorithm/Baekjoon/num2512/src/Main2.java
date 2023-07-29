import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/2512
1초 - 128MB
N은 3 이상 10,000 이하
값들은 모두 1 이상 100,000 이하
정수 M이 주어진다. M은 N 이상 1,000,000,000 이하

O(n)

실패! 다음에 재도전
-> (left + right) / 2 = 임계치 ((( 이분 탐색 )))
( https://www.acmicpc.net/board/view/112445 )
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int max = 0;
        float avg;
        float remain = 0;
        int overCount = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();

        // 최대 값 저장 및 배열에 모든 수 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            max = Math.max(max, num);
            arrayList.add(num);
        }

        // 입력 최대의 평균 및 해당 값 넘는 수 있는지 계산
        st = new StringTokenizer(br.readLine());
        avg = (float) Integer.parseInt(st.nextToken()) / N;
        for (int num : arrayList) {
            if (num > avg) overCount++;
            else remain += (avg - (float) num);
        }

        // 평균 다시 계산 및 Over 된 경우 있는지 확인
        boolean status = false;
        avg += (remain / overCount);
        int intAvg = (int) avg;

        for (int num : arrayList) {
            if (num > intAvg) status = true;
        }

        // Over 되었으면 평균 , 아니면 최대값 출력
        if (status) System.out.println(intAvg);
        else System.out.println(max);
    }
}
