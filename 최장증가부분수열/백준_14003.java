package LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        int[] idx = new int[N];

        Arrays.fill(dp, 1000000001);
        Arrays.fill(idx, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for(int i = 0 ; i < N ; i++){
            if(count == 0){
                dp[count] = arr[i];
                idx[i] = count;
                count++;
                continue;
            }

            if(dp[count - 1] < arr[i]){
                dp[count] = arr[i];
                idx[i] = count;
                count++;
            }else{
                int index = Arrays.binarySearch(dp, arr[i]);
                if(index < 0){
                    index = (index + 1) * -1;
                }
                dp[index] = arr[i];
                idx[i] = index;
            }
        }

        sb.append(count).append("\n");

        Stack<Integer> s = new Stack<>();
        for(int i = N - 1 ; i >= 0 ; i--){
            if(count < 0) break;
            if(idx[i] == count - 1){
                s.push(arr[i]);
                count--;
            }
        }
        while(!s.empty()) {
            sb.append(s.pop() + " ");
        }
        System.out.println(sb);
    }
}
