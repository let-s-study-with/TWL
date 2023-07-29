/*
https://www.acmicpc.net/problem/9372

아이디어
dfs

자료구조
배열

시간복잡도
O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[] booleans;
    static List<Integer>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            booleans = new boolean[N+1];
            lists = new ArrayList[N+1];
            for(int i = 1 ; i < N+1 ; i++){
               lists[i] = new ArrayList<Integer>();
            }

            while (M-- > 0){
                st = new StringTokenizer(br.readLine());

                int c1 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());

                lists[c1].add(c2);
                lists[c2].add(c1);
            }

            sb.append(dfs(1, 0)).append("\n");
        }

        System.out.println(sb);
    }

    public static int dfs(int index, int count){
        booleans[index] = true;

        for (int num : lists[index]){
            if (booleans[num]) continue;

            count = dfs(num, count+1);
        }

        return count;
    }
}
