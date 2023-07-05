/*
https://www.acmicpc.net/problem/1080
2초 - 128MB
행렬 크기 N * M ( 1 <= N , M <= 50 )

Arrays.deeptoequals 메소드로 코드 개선

아이디어
1. 3x3 뒤집기 가능한 범위에서만 for 문
1-1. N = 0 ~ N-3 뒤집어야되면 3x3 뒤집고 count 증가
2. M = 1 ~ M-3 까지 (1) 진행
3. 뒤집기 모두 수행했다면 두 배열 일치 확인

O(N*M) - 최악 50^2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static BufferedReader br;
    static StringTokenizer st;
    static int A[][];
    static int B[][];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        B = new int[N][M];

        createArray(A);
        createArray(B);

        // 1 & 2. 뒤집기 가능한 범위에서 뒤집기 수행
        for (int i = 0; i < N - 2; i++) {
            // 1-1. 가로 길이 3 이전 확인 ( 뒤집기 여부 확인 및 수행 )
            int j = 0;
            for (; j < M - 2; j++) {
                if (isMismatch(i, j)) doTurn(i, j);
            }
        }

        // 3. 뒤집기 완료 이후 일치 확인
        if (Arrays.deepEquals(A, B)) System.out.println(answer);
        else System.out.println(-1);
    }

    public static void createArray(int[][] arrays) throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            for (int j = 0; j < M; j++) {
                arrays[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
    }

    public static void doTurn(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (A[x + i][y + j] == 1) A[x + i][y + j] = 0;
                else A[x + i][y + j] = 1;
            }
        }
        answer++;
    }

    public static boolean isMismatch(int x, int y) {
        if (A[x][y] != B[x][y]) return true;
        else return false;
    }
}
