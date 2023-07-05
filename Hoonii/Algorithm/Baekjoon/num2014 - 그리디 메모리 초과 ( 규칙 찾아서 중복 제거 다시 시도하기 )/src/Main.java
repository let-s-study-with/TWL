/*
https://www.acmicpc.net/problem/2014
2초 - 128MB
K 개의 소수 ( 1 <= K <= 100 ) , 출력할 소수의 순번 ( 1 <= N <= 100,000)
1<= 각 소수의 수 <= 541

아이디어
1. ArrayList 소수 정렬 및 처음 경우 TreeSet 입력
2. TreeSet 가장 앞 빼면서 ArrayList 곱한 값 TreeSet 입력 ( O (K) )
2-1. 단 , 메모리 초과 방지를 위해 count 된 만큼 필요한 개수만 tree 에 유지
3. N 만큼 반복 ( O (N) )

O(N * K)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> num = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            num.add(Integer.valueOf(st.nextToken()));
        }

        Collections.sort(num);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(num);
        HashSet<Integer> hashSet = new HashSet<>(num);

        System.out.println(hashSet);

        int count = 0;
        int sosoo = 0;
        while (count < N) {
            count++;

            sosoo = priorityQueue.poll();

            for (int value : num){
                int result = sosoo * value;
                if (hashSet.contains(result)) continue;
                else if (sosoo >= Integer.MAX_VALUE) continue;
                else {
                    priorityQueue.add(result);
                    hashSet.add(result);
                }
            }

//            for (int value : num) {
//                int result = sosoo * value;
//
//                // 메모리 초과 방지 - 필요한 경우의 수만 저장
//                int large = treeSet.pollLast();
//                if (large <= result){
//                    treeSet.add(large);
//                    continue;
//                }
//
//                treeSet.add(result);
//            }
        }

        System.out.println(sosoo);
    }
}