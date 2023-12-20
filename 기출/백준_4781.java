package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_4781 {
    public static class Candy{
        int c,p;

        public Candy(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken().replace(".", ""));

            if(n == 0) break;

            int[] dp = new int[m+1];

            Candy[] arr = new Candy[n];

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken().replace(".", ""));
                arr[i] = new Candy(c, p);
            }

            for(int i = 1 ; i <= m ; i++ ){
                for(int j = 0 ; j < n ; j++){
                    if(i - arr[j].p >= 0){
                        dp[i] = Math.max(dp[i-arr[j].p] + arr[j].c, dp[i]);
                    }
                }
            }
            sb.append(dp[m]).append("\n");
        }
        System.out.println(sb);
    }
}
