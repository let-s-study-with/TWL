/*
hashmap vs arraylist 테스트 용 제출
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<String> list = new ArrayList<>();
        while (N-- > 0) {
            list.add(br.readLine());
        }

        int answer = 0;
        while (M-- > 0) {
            if (list.contains(br.readLine())) answer++;
        }

        System.out.println(answer);
    }
}
