import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/11723
1.5 초 - 4 MB --> 메모리가 매우 작음 ( Boolean[] 배열 사용 시 안된다는 경우가 있는 듯 )
연산의 수 M (1 ≤ M ≤ 3,000,000)

O(N)


===
Bit Mask 연산
https://myeongju00.tistory.com/30

StringBuilder 썼더니 Bit Mask 없이도 돌아감 .....

 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        int A = 0;
        while (num-- > 0) {
            st = new StringTokenizer(br.readLine());

            String action = st.nextToken();

            if (action.equals("all")) {
                A = (1 << 21) - 1;
                continue;
            } else if (action.equals("empty")) {
                A = 0;
                continue;
            }
            int N = Integer.parseInt(st.nextToken());

            switch (action) {
                case "add": {
                    A |= (1 << N);
                    break;
                }
                case "check": {
                    if ((A & (1 << N)) != 0) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                }
                case "remove": {
                    A &= ~(1 << N);
                    break;
                }
                case "toggle": {
                    A ^= (1 << N);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}
