/*
https://www.acmicpc.net/problem/5397
1초 - 256MB
테스트 케이스 T
입력한 문자열 길이 L ( 1 <= L <= 1,000,000 ) [ 백스페이스는 '-' 화살표는 '<' , '>' ]

아이디어
1. 기능 구현

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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            LinkedList<Character> linkedList = new LinkedList<>();
            ListIterator<Character> listIterator = linkedList.listIterator();

            String password = st.nextToken();
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == '<') {
                    if (listIterator.hasPrevious()){
                        listIterator.previous();
                    }
                }
                else if (password.charAt(i) == '>'){
                    if(listIterator.hasNext()){
                        listIterator.next();
                    }
                }
                else if (password.charAt(i) == '-') {
                    if (listIterator.hasPrevious()){
                        listIterator.previous();
                        listIterator.remove();
                    }
                } else {
                    listIterator.add(password.charAt(i));
                }
            }
            for (char c : linkedList){
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
