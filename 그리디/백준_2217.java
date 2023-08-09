package codingtest;

import java.util.*;
import java.io.*;

public class Baekjoon_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ropes = new int[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        int result = 0;
        int k = 0;
        Arrays.sort(ropes);
        for (int i = n - 1; i >= 0; i--) {
            k++;
            result = Math.max(ropes[i] * k, result);
        }
        System.out.println(result);
    }
}
