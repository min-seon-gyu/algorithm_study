package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_1106 {
    public static class Item implements Comparable<Item>{
        int cost;
        int count;

        public Item(int cost, int count) {
            this.cost = cost;
            this.count = count;
        }

        @Override
        public int compareTo(Item o) {
            if(this.cost == o.cost){
                return this.count - o.count;
            }
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 늘려야 하는 고객 수
        int C = Integer.parseInt(st.nextToken());
        // 홍보가 가능한 도시 수
        int N = Integer.parseInt(st.nextToken());

        Item[] city = new Item[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            city[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }


        int[] dp = new int[C+1];


        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;


        for(int i = 0 ; i < city.length ; i++){
            int cost = city[i].cost;
            int count = city[i].count;

            while(true){
                boolean check = false;

                if(count > C){
                    dp[C] = Math.min(dp[C], dp[count-city[i].count] + cost);
                    check = true;
                }else{
                    dp[count] = Math.min(dp[count], dp[count-city[i].count] + cost);
                }
                count += city[i].count;

                if(check) break;
            }
        }

        System.out.println(dp[C]);
    }
}
