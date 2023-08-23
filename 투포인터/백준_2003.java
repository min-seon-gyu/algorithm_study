import java.util.*;
import java.io.*;

public class 백준_2003 {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(search());
    }

    public static int search() {
        int start = 0;
        int end = 0;
        int sum = 0;
        int result = 0;
        while (true) {
            if (sum >= m) {
                sum -= arr[start++];
            } else if (end == n) {
                break;
            } else {
                sum += arr[end++];
            }

            if (sum == m)
                result++;
        }

        return result;
    }
}
