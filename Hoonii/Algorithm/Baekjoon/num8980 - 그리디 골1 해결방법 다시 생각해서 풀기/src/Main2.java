/*
https://www.acmicpc.net/problem/8980
1초 - 128MB
마을 수 N 은 2이상 2,000이하 정수이고, 트럭 용량 C는 1이상 10,000이하 정수
각줄 박스 정보의 개수 M이 주어진다. M은 1이상 10,000이하 정수

아이디어
1. 각 마을에서 C 를 기반으로 뒷 마을로 보낼 수 있는 최대 수량 저장
2. 1 ~ N 마을까지 돌면서 택배 수량 계산
2-1. 만약 택배가 가득 채워지지 않았다면 앞 마을에서 가져올 수 있는 수량 가져옴

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[][] info = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            info[start][end] = count;
        }

        int answer = 0;
        int current = 0;
        int[] done = new int[N + 1];
        int[] before = new int[N + 1];
        for (int dst = 2; dst < N + 1; dst++) {
            int max = 0;
            for (int src = dst - 1; src > dst; src--) {
                max = Math.max(before[src], max);
                // 현재 화물에 담은 양이 최대보다 작으면
                if (current < C && max != C) {
                    // 앞에서부터 이미 최대양만큼 보냈으면 패스
                    if (done[src] == C) continue;

                    // 앞에서부터 해당 도착지까지 보내는게 출발지에서 보낸 총량보다 크면 총량까지만 보냄
                    if (info[src][dst] >= C - done[src]) {
                        current += C - done[src];
                        done[src] = C;
                    }
                    // 앞에서부터 해당 도착지까지 보내는게 출발지에서 보낸 총량보다 작으면 보낼 수 있는거 다보냄
                    else {
                        current += info[src][dst];
                        done[src] += info[src][dst];
                    }
                } else {
                    for (int i = 1; i < src; i++) done[i] = C;
                    break;
                }
            }

            answer += current;
            before[dst] = current;
            current = 0;
        }

        System.out.println(Arrays.toString(done));
        System.out.println(answer);
    }
}
