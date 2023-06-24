import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/8979
1초 - 128MB
첫 줄은 국가의 수 N(1 ≤ N ≤ 1,000)과 등수를 알고 싶은 국가 K(1 ≤ K ≤ N)
전체 메달 수의 총합은 1,000,000 이하

Collection sort - O(nlog(n))

 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int countNum = Integer.parseInt(st.nextToken());
        int resultNum = Integer.parseInt(st.nextToken());

        ArrayList<Country> arrayList = new ArrayList<>();

        // Country 정보 저장 Class 배열을 ArrayList 에 저장
        for (int i = 1; i <= countNum; i++) {
            st = new StringTokenizer(br.readLine());

            Country country = new Country(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            arrayList.add(country);
        }

        // ArrayList 메달 순서로 정렬 및 앞 순서와 메달이 동일하면 체크
        Collections.sort(arrayList, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.gold == o2.gold) {
                    if (o1.silver == o2.silver) {
                        return o2.bronze - o1.bronze;
                    }
                    return o2.silver - o1.silver;
                }
                return o2.gold - o1.gold;
            }
        });

        // 등수 계산 및 결과 출력
        int index = 0;
        int gold = 0, silver = 0, bronze = 0;
        int before = 0;
        for (Country country : arrayList) {
            index++;

            if (gold == country.gold && silver == country.silver && bronze == country.bronze) {
                country.grade = before;
            } else {
                gold = country.gold;
                silver = country.silver;
                bronze = country.bronze;

                country.grade = index;
                before = index;
            }

            if (country.index == resultNum) {
                sb.append(country.grade);
                break;
            }
        }

        System.out.println(sb);
    }
}

class Country {
    int index;
    int grade;
    int gold;
    int silver;
    int bronze;

    Country(int index, int gold, int silver, int bronze) {
        this.index = index;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }
}
