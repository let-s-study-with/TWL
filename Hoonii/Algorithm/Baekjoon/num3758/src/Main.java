/*
https://www.acmicpc.net/problem/3758
1초 - 128MB
첫 번째 줄에는 테스트 데이터의 수를 나타내는 정수 T
첫 번째 줄에는 팀의 개수 n, 문제의 개수 k, 당신 팀의 ID t, 로그 엔트리의 개수 m을 나타내는 4 개의 정수
3 ≤ n, k ≤ 100, 1 ≤ t ≤ n, 3 ≤ m ≤ 10,000
각 줄에는 팀 ID i, 문제 번호 j, 획득한 점수 s를 나타내는 세 개의 정수
1 ≤ i ≤ n, 1 ≤ j ≤ k, 0 ≤ s ≤ 100

O (n log(n)) - 각 Case 당 시간 복잡도
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        while (N-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            /*
            팀 정보 저장 2차원 배열
            -> 문제 1
            -> 문제 ...
            -> 문제 n
            -> 문제 점수 합
            -> 문제 제출 수
            -> 문제 마지막 제출
            -> 팀 순위
             */
            int[][] table = new int[k+4][m];

            // 문제 점수 저장
            for (int i = 0 ; i < m ; i++){
                st = new StringTokenizer(br.readLine());

                int teamId = Integer.parseInt(st.nextToken()) - 1;
                int problemId = Integer.parseInt(st.nextToken()) - 1;
                int count = Integer.parseInt(st.nextToken());

                table[problemId][teamId] = Math.max(table[problemId][teamId], count);
                table[n+1][teamId]++;
                table[n+2][teamId] = i;
            }

            // 각 팀 모든 문제 점수 합 저장
            for (int i = 0 ; i < k ; i++){
                int total = 0;
                for (int j = 0 ; j < n ; j++){
                    total += table[j][i];
                }
                table[n][i] = total;
            }

            // 순위 저장을 위한 teamId 배열
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0 ; i < n ; i++){
                arrayList.add(i);
            }

            // 순위 비교 ( 총 합 -> 카운트 -> 마지막 제출 )
            Collections.sort(arrayList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (table[n][o1] == table[n][o2]){
                        if (table[n+1][o2] == table[n+1][o1]){
                            return table[n+2][o1] - table[n+2][o2];
                        }
                        return table[n+1][o1] - table[n+1][o2];
                    }
                    return table[n][o2] - table[n][o1];
                }
            });

            // 순위 저장
            int grade = 1;
            for (int i : arrayList){
                table[n+3][i] = grade;
                grade++;
            }
            sb.append(table[n+3][t-1]).append("\n");
        }
        System.out.println(sb);
    }
}
