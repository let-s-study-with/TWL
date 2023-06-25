/*
https://www.acmicpc.net/problem/9017
1초 - 128MB
참여자 수 N (6 ≤ N ≤ 1,000)
팀 수 M(1 ≤ M ≤ 200)

O(N) - 각 케이스
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int caseNum = Integer.parseInt(st.nextToken());

        while (caseNum-- > 0) {
            ArrayList<Integer> arrayList = new ArrayList<>();

            HashMap<Integer, Integer> teamCount = new HashMap<>();
            HashMap<Integer, Integer> teamGrade = new HashMap<>();
            HashMap<Integer, Integer> teamGradeCount = new HashMap<>();
            HashMap<Integer, Integer> team5th = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            // 각 팀 선수 카운트 및 도착 순서 저장
            st = new StringTokenizer(br.readLine());
            int copyN = N;
            while (copyN-- > 0) {
                int person = Integer.parseInt(st.nextToken());

                teamCount.put(person, teamCount.getOrDefault(person, 0) + 1);
                teamGradeCount.put(person, 1);
                arrayList.add(person);
            }

            // 각 팀 점수 계산
            int grade = 1;
            while (++copyN < N) {
                int person = arrayList.get(copyN);

                if (teamCount.get(person) == 6 && teamGradeCount.get(person) < 5) {
                    teamGrade.put(person, teamGrade.getOrDefault(person, 0) + grade);
                    teamGradeCount.put(person, teamGradeCount.get(person) + 1);
                    grade++;
                }
                else if (teamCount.get(person) == 6 && teamGradeCount.get(person) == 5) {
                    team5th.put(person, copyN);
                    teamGradeCount.put(person, 6);
                    grade++;
                }

                else if (teamCount.get(person) == 6 && teamGradeCount.get(person) == 6) {
                    grade++;
                }
            }

            // 우승 팀 계산
            int min = Integer.MAX_VALUE;
            int win = 0;
            for (Map.Entry<Integer,Integer> entry : teamGrade.entrySet()){
                int team = entry.getKey();
                int teamValue = entry.getValue();

                if (min == teamValue) {
                    win = team5th.get(win) < team5th.get(team) ? win : team;
                }
                else if (teamValue < min) {
                    min = teamValue;
                    win = team;
                }
            }
            sb.append(win).append("\n");
        }
        System.out.println(sb);
    }
}
