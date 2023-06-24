import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/9655
1초 - 128MB
1 ≤ N ≤ 1000

log(n)

 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] dy = new int[1001];

        dy[1] = 1;
        dy[2] = 2;
        dy[3] = 1;
        dy[4] = 2;
        dy[5] = 1;
        dy[6] = 2;
        dy[7] = 1;
        dy[8] = 2;

        for (int i = 9; i <= N; i++) {
            if (dy[i - 1] == 2 || dy[i - 3] == 2) dy[i] = 1;
            else dy[i] = 2;
        }

        if (dy[N]==1) sb.append("SK");
        else sb.append("CY");

        System.out.println(sb);
    }
}
