/*
https://www.acmicpc.net/problem/1026
2초 - 128MB
문자 길이 N은 50보다 작거나 같은 자연수

아이디어
1. A 배열 오름차순 , B 배열 내림차순 정렬
2. 같은 인덱스 값 곱하고 합 저장

O( N log (N)) - 정렬
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];

        createArrays(A);
        createArrays(B);

        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i] * B[i];
        }

        System.out.println(sum);
    }

    public static void createArrays(Integer[] array) throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
    }
}
