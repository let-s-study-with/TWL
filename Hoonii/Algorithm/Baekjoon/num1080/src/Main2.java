/*
https://www.acmicpc.net/problem/1080
2초 - 128MB
행렬 크기 N * M ( 1 <= N , M <= 50 )

아이디어
0. 행렬 N >= 3 , M >= 3 체크 후 false 라면 A , B 동일 체크 실패 시 -1 , 성공 시 0 리턴
1. M = 0 , N = 0 ~ N-3 뒤집 여부 체크 및 true 라면 3x3 뒤집고 count 증가
1-1. N = N-3 도착 시 N-2 , N-1 A , B 동일 체크 -> false 시 -1 리턴
2. M = 1 ~ M-3 까지 (1) 진행
3. M = M-3 , N = N-3 까지 (1) 정상 진행 시 M = M-2 ~ M-1 , N = N-3 ~ N-1 A , B 동일 체크 -> false 시 -1 리턴

O(N*M) - 최악 50^2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
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

        // 0. 뒤집기 불가능한 경우 체크
        if (N < 3 || M < 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (isMismatch(i, j)) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
            System.out.println(answer);
            return;
        }

        // 2. 뒤집기 가능한 경우 범위
        for (int i = 0; i < N - 2; i++) {
            // 1. 가로 길이 3 이전 확인 ( 뒤집기 가능 )
            int j = 0;
            for (; j < M - 2; j++) {
                if (isMismatch(i, j)) doTurn(i, j);
            }

            // 1-1. 가로 길이 3 이후 확인 ( 뒤집기 불가 )
            if (isMismatch(i, j) || isMismatch(i, j + 1)) {
                System.out.println(-1);
                return;
            }
        }

        // 3. 뒤집기 완료 이후 일치 확인
        for (int i = N - 2; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isMismatch(i, j)) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);
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
