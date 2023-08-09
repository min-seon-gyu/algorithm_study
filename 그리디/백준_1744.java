package codingtest;

import java.io.*;
import java.util.*;

public class 백준_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        Arrays.sort(arr);

        int result = 0;
        for (int i = 0; i < n; i += 2) {
            if (arr[i] > 0) break;

            if (i + 1 >= n || arr[i + 1] > 0) {
                result += arr[i];
            } else {
                result += arr[i] * arr[i + 1];
            }
        }
        for (int i = n - 1; i >= 0; i -= 2) {
            if (arr[i] <= 0) break;

            if (i - 1 < 0 || arr[i - 1] <= 0) {
                result += arr[i];
            } else {
                int sum = arr[i] + arr[i - 1];
                int pro = arr[i] * arr[i - 1];
                result += Math.max(sum, pro);
            }
        }
        System.out.println(result);
    }
}
