/*
https://www.acmicpc.net/problem/4659
1초 - 128MB
마지막 테스트 케이스는 end이며, 패스워드는 한글자 이상 20글자 이하의 문자열

각 case 별 - O(n)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        while (true) {
            int totalMo = 0;
            int mo = 0;
            int ja = 0;
            char before = 'e';
            boolean status = true;

            st = new StringTokenizer(br.readLine());
            String pwd = st.nextToken();

            if (pwd.equals("end")) break;

            for (int i = 0; i < pwd.length(); i++) {
                char c = pwd.charAt(i);

                // 모음 / 자음 연속 카운트
                if (c == 'a' || c == 'e' || c == 'o' || c == 'u' || c == 'i') {
                    totalMo++;
                    mo++;
                    ja = 0;
                } else {
                    mo = 0;
                    ja++;
                }

                // 'e' or 'o' 제외 연속 2번인 경우
                if (before == c) {
                    if (!(before == 'e' || before == 'o')){
                        status = false;
                        break;
                    }
                }

                // 모음 / 자음 연속 3번인 경우
                if (mo == 3 || ja == 3){
                    status = false;
                    break;
                }

                before = c;
            }

            if (totalMo == 0) status = false;

            if (status) sb.append("<").append(pwd).append("> is acceptable.\n");
            else sb.append("<").append(pwd).append("> is not acceptable.\n");
        }

        System.out.println(sb);
    }
}
