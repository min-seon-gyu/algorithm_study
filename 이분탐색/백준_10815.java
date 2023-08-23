import java.io.*;
import java.util.*;

public class 백준_10815 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] targets = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int target : targets) {
            sb.append(search(target)).append(" ");
        }
        System.out.println(sb);
    }

    public static int search(int target) {
        int mid;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == target) {
                return 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }
}
