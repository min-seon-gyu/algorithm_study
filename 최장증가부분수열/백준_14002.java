package LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
        int max = 1;
        for(int i = 1 ; i < N ; i++){
            dp[i] = 1;
            for(int j = i - 1 ; j >= 0 ; j--){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max ,dp[i]);
                }
            }
        }

        sb.append(max).append("\n");
        Stack<Integer> s = new Stack<>();

        for(int i = N-1 ; i >= 0 ; i--){
            if(max == 0) break;

            if(dp[i] == max){
                s.push(arr[i]);
                max--;
            }
        }

        while(!s.empty()){
            sb.append(s.pop() + " ");
        }
        System.out.println(sb);
    }
}
