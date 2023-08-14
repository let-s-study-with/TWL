/*
https://leetcode.com/problems/sort-characters-by-frequency/

아이디어
각 문자 횟수 map 저장
문자 - 횟수 -> 내림차순 PQ 저장
횟수 만큼 문자 붙혀서 출력

자료구조
Map
내림차순 PQ

시간복잡도
O (N log N )
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<myCharator> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            pq.add(new myCharator(entry.getKey(), entry.getValue()));
        }

        StringBuilder answer = new StringBuilder();
        while (!pq.isEmpty()) {
            myCharator mc = pq.poll();

            while (mc.frequency-- > 0) {
                answer.append(mc.c);
            }
        }

        return answer.toString();
    }
}

class myCharator implements Comparable<myCharator> {
    char c;
    int frequency;

    myCharator(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(myCharator o) {
        return o.frequency - this.frequency;
    }
}
