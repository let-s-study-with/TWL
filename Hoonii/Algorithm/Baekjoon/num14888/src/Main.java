/*
https://www.acmicpc.net/problem/14888

아이디어
백트래킹 -> 4가지 연산자를 모든 경우 계산

자료구조
배열

시간복잡도
O(4^N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int[] operators = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        backtrack(0, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    public static void backtrack(int index, int result) {
        if (index == N - 1) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] == 0) continue;

            operators[i]--;
            backtrack(index + 1, operateResult(i, index + 1, result));
            operators[i]++;
        }
    }

    public static int operateResult(int operator, int index, int result) {
        if (operator == 0) {
            return result + nums[index];
        } else if (operator == 1) {
            return result - nums[index];
        } else if (operator == 2) {
            return result * nums[index];
        } else {
            return result / nums[index];
        }
    }
}
