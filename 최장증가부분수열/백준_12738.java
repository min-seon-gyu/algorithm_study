package LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_12738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        int count = 0;

        Arrays.fill(dp, 1000000001);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < N ; i++){
            if(count == 0){
                dp[count] = arr[i];
                count++;
                continue;
            }

            if(dp[count-1] < arr[i]){
                dp[count] = arr[i];
                count++;
            }else{
                int idx = Arrays.binarySearch(dp, arr[i]);
                if (idx < 0) {
                    idx = (idx + 1) * -1;
                }
                dp[idx] = arr[i];
            }
        }
        System.out.println(count);
    }
}
