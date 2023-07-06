/*
https://www.acmicpc.net/problem/1781
2초 - 256MB
숙제의 개수 N (1 ≤ N ≤ 200,000)
문제에 대한 데드라인과 풀면 받을 수 있는 컵라면 수

아이디어
1. 문제 정보 컵라면 기준 내림차순 정렬


O(N^2)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2_TimeOut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] problems = new int[N][2];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            problems[i][0] = num;
            problems[i][1] = count;
        }

        Arrays.sort(problems, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            }
        });

        int[] use = new int[N+1];
        int answer = 0;
        for (int i = 0 ; i < N ; i++){
            int num = problems[i][0];
            int count = problems[i][1];

            if (use[num] < num){
                answer += count;

                for (int j = N ; j > num-1 ; j--){
                    use[j]++;
                    if (use[j] == j) {
                        for (int k = 1 ; k < j ; k++){
                            use[k] = k;
                        }
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
