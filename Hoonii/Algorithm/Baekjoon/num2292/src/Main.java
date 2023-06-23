import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://www.acmicpc.net/problem/2292
2초 - 127MB
N(1 ≤ N ≤ 1,000,000,000)

O(1) -> 최대 18258
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Long count = 1L;
        int index = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while (count < n) {
            count += (6L * index);
            index++;
        }

        System.out.println(index);
    }
}
