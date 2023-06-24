/*
https://www.acmicpc.net/problem/1244
2초 - 128MB

O([switch수][person수])
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] status;
    static int switchSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        switchSize = Integer.parseInt(st.nextToken());

        status = new boolean[switchSize];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < switchSize; i++) {
            status[i] = Integer.parseInt(st.nextToken()) == 1;
        }

        st = new StringTokenizer(br.readLine());
        int personSize = Integer.parseInt(st.nextToken());

        for (int i = 0; i < personSize; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int switchNum = Integer.parseInt(st.nextToken());

            doAction(gender, switchNum);
        }

        for (int i = 0; i < switchSize; i++) {
            if (i!=0 && i % 20 == 0) sb.append("\n");
            sb.append(status[i] ? 1 : 0).append(" ");
        }

        System.out.println(sb);
    }

    public static void doAction(int gender, int switchNum) {
        int N = switchNum;
        switchNum = switchNum - 1;

        // 남학생의 경우
        if (gender == 1) {
            int index = 0;
            while (switchNum + (N * index) < switchSize) {
                status[switchNum + (N * index)] = !status[switchNum + (N * index)];
                index++;
            }

        }
        // 여학생의 경우
        else {
            status[switchNum] = !status[switchNum];
            // 대칭 찾기
            for (int i = 1; i < switchSize; i++) {
                if (switchNum - i < 0 || switchNum + i > switchSize - 1) break;
                if (status[switchNum - i] == status[switchNum + i]) {
                    status[switchNum - i] = !status[switchNum - i];
                    status[switchNum + i] = !status[switchNum + i];
                } else break;
            }
        }
    }
}
