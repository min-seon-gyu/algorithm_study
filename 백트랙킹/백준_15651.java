import java.io.*;
import java.util.*;

public class Baekjoon_15651 {

    static int n, m;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            result = new int[m];
            br.close();
            dfs(0);
            System.out.print(sb.toString());
        } catch (IOException ignore) {
        }
    }

    public static void dfs(int index) {
        if (index == m) {
            for (int value : result) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            result[index] = i + 1;
            dfs(index + 1);
        }
    }
}