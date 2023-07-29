/*
https://www.acmicpc.net/problem/3758

아이디어
기능 구현

자료구조
배열

시간복잡도
O(N log N) or O(log 수)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int totalTeam = Integer.parseInt(st.nextToken());
            int totalProblem = Integer.parseInt(st.nextToken());
            int myTeam = Integer.parseInt(st.nextToken()) - 1;
            int submitLog = Integer.parseInt(st.nextToken());

            List<Team> list = new ArrayList<>();
            for (int i = 0; i < totalTeam; i++) {
                list.add(new Team(i));
            }

            int index = 1;
            while (submitLog-- > 0) {
                st = new StringTokenizer(br.readLine());

                int teamId = Integer.parseInt(st.nextToken()) - 1;
                int problemId = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());

                Team team = list.get(teamId);
                int previousScore = team.submitScore.getOrDefault(problemId, 0);
                if (previousScore < score) {
                    team.submitScore.put(problemId, score);
                    team.totalScore += score - previousScore;
                }

                team.submitCount++;
                team.lastSubmit = index++;
            }

            list.sort(new Comparator<Team>() {
                @Override
                public int compare(Team o1, Team o2) {
                    if (o1.totalScore == o2.totalScore) {
                        if (o1.submitCount == o2.submitCount) {
                            return o1.lastSubmit - o2.lastSubmit;
                        }
                        return o1.submitCount - o2.submitCount;
                    }
                    return o2.totalScore - o1.totalScore;
                }
            });

            int grade = 1;
            for (Team team : list) {
                if (team.teamId == myTeam) {
                    sb.append(grade).append("\n");
                    break;
                }
                grade++;
            }
        }

        System.out.println(sb);
    }
}

class Team {
    int teamId;
    HashMap<Integer, Integer> submitScore = new HashMap<>();
    int submitCount;
    int totalScore;
    int lastSubmit;

    Team(int teamId) {
        this.teamId = teamId;
    }
}
