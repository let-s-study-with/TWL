/*
https://www.acmicpc.net/problem/7568
1초 - 128MB
2 ≤ N ≤ 50
10 ≤ x, y ≤ 200

O((n^2)/2)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int countNum = Integer.parseInt(st.nextToken());
        int N = countNum;

        ArrayList<Person> arrayList = new ArrayList<>();

        while (countNum-- > 0) {
            st = new StringTokenizer(br.readLine());

            Person person = new Person(N - countNum + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            arrayList.add(person);
        }

        Collections.sort(arrayList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.weight - o1.weight;
            }
        });

        for (int i = N-1 ; i >= 0 ; i-- ){
            int height = arrayList.get(i).height;
            int weight = arrayList.get(i).weight;
            int grade = 1;

            for (int j = i-1 ; j >= 0 ; j-- ){
                if (arrayList.get(j).height > height && arrayList.get(j).weight > weight) grade++;
            }
            arrayList.get(i).grade = grade;
        }

        Collections.sort(arrayList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.index - o2.index;
            }
        });

        for (Person person : arrayList){
            sb.append(person.grade).append(" ");
        }

        System.out.println(sb);
    }
}

class Person {
    int index;
    int weight;
    int height;
    int grade;

    Person(int index, int weight, int height) {
        this.index = index;
        this.weight = weight;
        this.height = height;
    }
}
