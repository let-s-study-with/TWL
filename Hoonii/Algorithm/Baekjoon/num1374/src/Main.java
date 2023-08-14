/*
https://www.acmicpc.net/problem/1374

아이디어
수업 시작 시간 기준 오름차순 PQ
하나씩 빼면서 endtime 내림차순 PQ 입력
가장 작은 endtime 갱신 (같은 수업 교실) or 새로운 수업 교실 추가

자료구조
내린차순 PQ
오름차순 PQ

시간복잡도
O(N log N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static PriorityQueue<Subject> subjects = new PriorityQueue<>();
    static PriorityQueue<Integer> classes = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            st.nextToken();
            subjects.add(new Subject(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while (!subjects.isEmpty()) {
            Subject subject = subjects.poll();

            if (!classes.isEmpty() && classes.peek() <= subject.startTime) {
                classes.poll();
            }

            classes.add(subject.endTime);
        }

        System.out.println(classes.size());
    }
}

class Subject implements Comparable<Subject> {
    int startTime;
    int endTime;

    Subject(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Subject o) {
        return this.startTime - o.startTime;
    }
}
