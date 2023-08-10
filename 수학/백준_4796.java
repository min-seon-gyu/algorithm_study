package codingtest;

import java.io.*;
import java.util.*;

public class 백준_4796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (l == 0 && p == 0 && v == 0) break;
            int sum = (v / p) * l + Math.min(v % p, l);
            sb.append("Case ").append(i).append(": ").append(sum).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}