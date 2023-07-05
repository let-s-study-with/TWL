/*
https://www.acmicpc.net/problem/10162
1초 - 64MB
요리시간 1 ≤ T ≤ 10,000

아이디어
1. 동전 그리디

O(1)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        int fiveM = 0, oneM = 0, tenS = 0;

        if (T >= 300){
            fiveM = T / 300;
            T %= 300;
        }
        if (T >= 60){
            oneM = T/60;
            T %= 60;
        }
        if (T >= 10){
            tenS = T / 10;
            T %= 10;
        }
        if (T == 0) System.out.println(fiveM + " " + oneM + " " + tenS);
        else System.out.println(-1);
    }
}
