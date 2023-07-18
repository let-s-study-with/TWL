/*
https://www.acmicpc.net/problem/2467

아이디어
투포인터

자료구조
배열

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        int index = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            nums[index] = Integer.parseInt(st.nextToken());
            index++;
        }

        int L = 0;
        int R = N - 1;
        int min = Integer.MAX_VALUE;
        int answerL = 0;
        int answerR = 0;
        while (L != R) {
            int positiveSum = Math.abs(nums[L] + nums[R]);

            if (positiveSum <= min) {
                min = positiveSum;
                answerL = nums[L];
                answerR = nums[R];
            }

            if (nums[L] + nums[R] > 0) {
                R--;
            } else {
                L++;
            }
        }

        System.out.println(answerL + " " + answerR);
    }
}
