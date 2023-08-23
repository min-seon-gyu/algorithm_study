package codingtest;

import java.io.*;
import java.util.*;

public class 백준_1806 {

    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(search());
    }

    private static int search() {
        int result = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (true) {
            if (sum >= s) {
                result = Math.min(result, end - start);
                sum -= arr[start++];
            } else if (end == n) {
                break;
            } else {
                sum += arr[end++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
