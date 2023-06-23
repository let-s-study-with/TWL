import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/10431
1초 - 256MB
테스트 케이스의 수 P (1 ≤ P ≤ 1000)
케이스 번호 T와 20개의 양의 정수

O(n^2) - n 최대 20

 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int caseNum = Integer.parseInt(st.nextToken());

        while (caseNum-- > 0) {
            st = new StringTokenizer(br.readLine());

            ArrayList<Integer> arrayList = new ArrayList<>();

            int head = Integer.parseInt(st.nextToken());

            int count = 0;

            for (int i = 0; i < 20; i++) {
                int height = Integer.parseInt(st.nextToken());

                arrayList.add(height);

                for (int j = 0; j < arrayList.size(); j++) {
                    if (arrayList.get(j) > height){
                        count += arrayList.size() - (j + 1);
                        break;
                    }
                }

                arrayList.sort(Comparator.naturalOrder());
            }

            sb.append(head).append(" ").append(count).append("\n");
        }

        System.out.println(sb);
    }
}
