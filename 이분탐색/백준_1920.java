import java.io.*;
import java.util.*;

public class 백준_1920 {
    static int[] arr1, arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr1);
        for (int target : arr2) {
            sb.append(search(target)).append("\n");
        }
        System.out.print(sb);
    }

    public static int search(int target) {
        int mid;
        int start = 0;
        int last = arr1.length - 1;

        while (start <= last) {
            mid = (start + last) / 2;
            if (arr1[mid] == target) {
                return 1;
            } else if (arr1[mid] > target) {
                last = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }
}
