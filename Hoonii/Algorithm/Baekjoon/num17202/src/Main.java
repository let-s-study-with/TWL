/*
https://www.acmicpc.net/problem/17202

아이디어
구현
?? 어떻게 dp 를 써야될 지 감이 안옴

자료구조
ArrayList

시간복잡도
O(2~16 수의 합) -> O(N)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num1 = br.readLine();
        String num2 = br.readLine();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(Integer.parseInt(String.valueOf(num1.charAt(i))));
            list.add(Integer.parseInt(String.valueOf(num2.charAt(i))));
        }

        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                list = new ArrayList<>(newList);
                newList = new ArrayList<>();

                if (list.size() == 2) break;

                i = -1;
                continue;
            }

            newList.add((list.get(i) + list.get(i + 1)) % 10);
        }

        int answer = list.get(0) * 10;
        answer += list.get(1);

        System.out.println(answer < 10 ? 0 + "" + answer : answer);
    }
}
