/*
https://www.acmicpc.net/problem/10816

아이디어
두 배열 정렬
L 위치 저장하며 이분탐색

자료구조
배열

시간복잡도
O(N log N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        br.readLine();
        List<Integer> list1 = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            list1.add(Integer.valueOf(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());

        List<Number> list2 = new ArrayList<>();
        int index = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            list2.add(new Number(index++, Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        index = 0;
        Number beforeNum = null;
        for (Number number : list2) {
            int num = number.number;
            int L = index;
            int R = list1.size();

            if (!Objects.isNull(beforeNum) && beforeNum.value == num){
                number.value = beforeNum.value;
                continue;
            }

            while (L < R) {
                int mid = (L + R) / 2;

                if (list1.get(mid) < num) {
                    index = mid + 1;
                    L = mid + 1;
                } else if (list1.get(mid) == num) L = mid + 1;
                else R = mid;
            }
            number.value = L - index;
            beforeNum = number;

            index = L;
        }

        Collections.sort(list2, new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                return o1.index - o2.index;
            }
        });

        list2.forEach(i -> sb.append(i.value).append(" "));

        System.out.println(sb);
    }
}

class Number implements Comparable {
    int index;
    int value;
    int number;

    public Number(int index, int number) {
        this.index = index;
        this.number = number;
    }

    @Override
    public int compareTo(Object o) {
        return this.number - ((Number) o).number;
    }
}
