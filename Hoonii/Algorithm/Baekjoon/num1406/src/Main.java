/*
https://www.acmicpc.net/problem/1406

아이디어
기능 구현

자료구조
링크드리스트
이터레이터

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        LinkedList<Character> list = new LinkedList<>();
        ListIterator<Character> listIterator = list.listIterator();

        String str = br.readLine();
        for (char c : str.toCharArray()) {
            listIterator.add(c);
        }

        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);

            if (c == 'L' && listIterator.hasPrevious()) {
                listIterator.previous();
            } else if (c == 'D' && listIterator.hasNext()) {
                listIterator.next();
            } else if (c == 'B' && listIterator.hasPrevious()) {
                listIterator.previous();
                listIterator.remove();
            } else if (c == 'P') {
                listIterator.add(st.nextToken().charAt(0));
            }
        }

        list.forEach(sb::append);

        System.out.println(sb);
    }
}
