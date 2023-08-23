import java.io.*;
import java.util.*;

// 정렬 후 lower bound 값 출력
public class 백준_18870 {
    static Integer[] copyArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        Set<Integer> set = new HashSet<>(); // 중복 제거, 중복의 좌표 값은 같은 좌표로 취급
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
        }

        copyArr = set.toArray(new Integer[0]);
        Arrays.sort(copyArr);
        StringBuilder sb = new StringBuilder();
        for (int target : arr) {
            sb.append(searchLowerBound(target)).append(" ");
        }
        System.out.println(sb);
    }

    public static int searchLowerBound(int target) {
        int mid;
        int start = 0;
        int end = copyArr.length - 1;
        while (start < end) {
            mid = (start + end) / 2;
            if (copyArr[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}