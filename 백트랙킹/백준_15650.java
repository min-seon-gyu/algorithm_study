import java.io.*;
import java.util.*;

public class Baekjoon_15650 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static boolean[] checked;
    static int[] result;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            checked = new boolean[n];
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
            if (!checked[i]) {
                if (index != 0 && result[index - 1] > i + 1)
                    continue;

                checked[i] = true;
                result[index] = i + 1;
                dfs(index + 1);
                checked[i] = false;
            }
        }
    }
}