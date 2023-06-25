/*
https://www.acmicpc.net/problem/17266
1초 - 256MB
첫 번째 줄에 굴다리의 길이 N 이 주어진다. (1 ≤ N ≤ 100,000)
두 번째 줄에 가로등의 개수 M 이 주어진다. (1 ≤ M ≤ N)
다음 줄에 M 개의 설치할 수 있는 가로등의 위치 x 가 주어진다. (0 ≤ x ≤ N)
가로등의 위치 x는 오름차순으로 입력

O(n)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());

        int max = 0;
        st = new StringTokenizer(br.readLine());

        // 첫 가로등
        int N = Integer.parseInt(st.nextToken());
        max = N * 2;
        int before = N;

        // 중간 가로등
        for (int i = 1; i < count; i++) {
            N = Integer.parseInt(st.nextToken());
            max = Math.max(max, N - before);
            before = N;
        }

        // 마지막 가로등
        max = Math.max(max, (length - N) * 2);

        System.out.println((int) Math.ceil((double) max / 2.0));
    }
}
