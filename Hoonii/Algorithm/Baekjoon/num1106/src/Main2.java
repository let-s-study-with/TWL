/*
https://www.acmicpc.net/problem/1106

아이디어
각 도시 ( 고객 / 비용 ) 비율이 좋은 것은 최대한 쓰는 방법
비율이 좋은 거부터 최대한 채우고 최소 비용 계산 -> 비율 좋은 거 하나씩 빼고 다음 비율 좋은거 최대한 채우고 또 최소 비용 계산 반복

--> 1% 실패 -> DP 풀이 방법 검색

자료구조
ArrayList

시간복잡도
O(N log N)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int C, N;
    static List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        System.out.println(computeMinPrice());
    }

    public static int computeMinPrice() {
        int customerCount = 0;
        int minPrice = Integer.MAX_VALUE;

        int casePrice = 0;
        for (Node node : list) {
            while (customerCount < C) {
                customerCount += node.customer;
                casePrice += node.price;
            }

            minPrice = Math.min(minPrice, casePrice);

            customerCount -= node.customer;
            casePrice -= node.price;
        }

        return minPrice;
    }
}

class Node implements Comparable<Node> {
    int price;
    int customer;
    float rate;

    public Node(int price, int customer) {
        this.price = price;
        this.customer = customer;
        this.rate = (float) customer / price;
    }

    @Override
    public int compareTo(Node o) {
        if (this.rate == o.rate) return o.price - this.price;
        return Float.compare(o.rate, this.rate);
    }
}
