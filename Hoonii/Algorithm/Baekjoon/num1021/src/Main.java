/*
https://www.acmicpc.net/problem/1021
2초 - 128MB
큐의 크기 N , 뽑으려는 수의 개수 M ( 1 <= M <= N <= 50 )

O ( M * N ) - 큐 M , for N/2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        while (M-- > 0) {
            queue.add(Integer.valueOf(st.nextToken()));
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            arrayList.add(i);
        }

        int answer = 0;
        int currentPos = 0;
        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (int i = 0; i <= arrayList.size() / 2; i++) {
                int L = currentPos + i > arrayList.size() - 1 ? currentPos + i - arrayList.size() : currentPos + i;
                int R = currentPos - (i + 1) < 0 ? currentPos - (i + 1) + arrayList.size() : currentPos - (i + 1);

                if (arrayList.get(L) == num) {
                    if (L != arrayList.size() - 1) currentPos = L;
                    else currentPos = 0;

                    arrayList.remove((Integer) num);
                    answer += i;
                    break;
                } else if (arrayList.get(R) == num) {
                    if (R != arrayList.size() - 1) currentPos = R;
                    else currentPos = 0;

                    arrayList.remove((Integer) num);
                    answer += i + 1;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
