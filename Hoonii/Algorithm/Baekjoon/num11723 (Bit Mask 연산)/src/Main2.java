import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashSet<Integer> hashSet = new HashSet<>();

        int num = Integer.parseInt(st.nextToken());

        while (num > 0) {
            st = new StringTokenizer(br.readLine());

            String action = st.nextToken();

            int N;
            if (action.equals("all") || action.equals("empty")) {
                N = 0;
            } else {
                N = Integer.parseInt(st.nextToken());
            }

            switch (action) {
                case "add" : {
                    hashSet.add(N);
                    break;
                }
                case "check" : {
                    if (hashSet.contains(N)) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                }
                case "remove" : {
                    hashSet.remove(N);
                    break;
                }
                case "toggle" : {
                    if (hashSet.contains(N)) hashSet.remove(N);
                    else hashSet.add(N);
                    break;
                }
                case "all" : {
                    for (int i = 0; i < 21; i++) {
                        hashSet.add(i);
                    }
                    break;
                }
                case "empty" : {
                    hashSet.clear();
                    break;
                }
                default : {
                    break;
                }
            }
            num--;
        }
        System.out.println(sb);
    }
}
