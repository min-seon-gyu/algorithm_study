import java.util.*;
import java.io.*;

public class Baekjoon_15654 {

    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static int[] values, result;
    static boolean[] checked;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            values = new int[n];
            result = new int[m];
            checked = new boolean[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                values[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(values);
            br.close();
            dfs(0);
            System.out.print(sb.toString());
        } catch (IOException ignore) {}
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
            if (!checked[i]) {
                checked[i] = true;
                result[index] = values[i];
                dfs(index + 1);
                checked[i] = false;
            }
        }
    }
}
