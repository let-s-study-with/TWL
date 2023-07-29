/*
https://github.com/AlgorithmReview-Organization-2/CodingReviewRepository/issues/217

근데 이거 무조건 0 노드는 리프노드로 동작해야 하는듯
4 3 일 때
0 1 , 0 2 , 0 3 도 정답 아닌가

아이디어
각 노드의 부모 노드 저장 후 출력

자료구조
배열

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parents = new int[N];
        parents[1] = 0;

        int parent = 1;
        int leafCount = 2;
        for (int i = 2; i < N; i++) {
            parents[i] = parent;

            if (leafCount < M) {
                leafCount++;
            } else {
                parent = i;
            }
        }

        for (int index = 1; index < N; index++) {
            sb.append(parents[index]).append(" ").append(index).append("\n");
        }

        System.out.println(sb);
    }
}
