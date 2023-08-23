import java.io.*;
import java.util.*;

public class 백준_20922 {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(search());
    }

    public static int search() {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int start = 0;
        int end = 0;
        while (end < n) {
            int key = arr[end];
            int value = map.getOrDefault(key, 0);
            if (value >= k) {
                result = Math.max(result, end - start);
                int startKey = arr[start];
                map.put(startKey, map.get(startKey) - 1);
                start++;
            } else {
                map.put(key, value + 1);
                end++;
            }
        }
        return Math.max(result, end - start);
    }
}
