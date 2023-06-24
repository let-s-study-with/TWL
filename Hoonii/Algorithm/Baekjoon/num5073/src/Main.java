import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/5073
1초 - 128M
1,000을 넘지 않는 양의 정수 3개

O(n) - case 수
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a==0 && b==0 && c==0) break;

            int max = a > b ? (a > c ? 0 : 2) : (b > c ? 1 : 2);

            if (max==0){
                if (a >= b+c){
                    System.out.println("Invalid");
                    continue;
                }
            } else if (max==1) {
                if (b >= a+c){
                    System.out.println("Invalid");
                    continue;
                }
            } else if (max==2) {
                if (c >= a+b){
                    System.out.println("Invalid");
                    continue;
                }
            }

            if (a==b && b==c) System.out.println("Equilateral");
            else if (a==b || b==c || a==c) System.out.println("Isosceles");
            else System.out.println("Scalene");

        }
    }
}
