/*
https://leetcode.com/problems/find-all-anagrams-in-a-string/

아이디어
투 포인터 - 슬라이딩 윈도
1. 처음 필요 (p의 원소) 배열 저장
2. L,R = 0 시작 , p 크기만큼 R 증가 -> 배열에 R 원소 있으면 뻄 , 없으면 notused 저장
3. p 크기 도착 후 배열 비어있으면 해당 L 정답
4. L 증가 -> notused 에 L 원소 있으면 notused 에서 뻄 , 아니면 뺴는데 사용한거니까 배열에 다시 추가

자료구조
배열
HashSet

시간 복잡도
O(N) , 하지만 if 문이 많았죠 ( 오래걸림 )
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Character> arrayList = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();

        for (char c : p.toCharArray()) {
            arrayList.add(c);
        }

        int L = 0;
        int R = 0;
        ArrayList<Character> notUsed = new ArrayList<>();
        while (true) {
            if (R < s.length() && R - L < p.length()) {
                if (arrayList.contains(s.charAt(R))) arrayList.remove((Character) s.charAt(R));
                else notUsed.add(s.charAt(R));

                R++;
            } else if (L <= s.length() - p.length()) {
                if (arrayList.isEmpty()) answer.add(L);

                if (notUsed.contains(s.charAt(L))) notUsed.remove((Character) s.charAt(L));
                else arrayList.add(s.charAt(L));

                L++;
            } else {
                break;
            }
        }

        return answer;
    }
}