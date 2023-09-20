package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_9205 {
    public static class Loc{
        int x,y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < t ; i++){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            int[][] bottle = new int[n][2];
            boolean[] check = new boolean[n];

            for(int j = 0 ; j < n ; j++){
                st = new StringTokenizer(br.readLine());
                bottle[j][0] = Integer.parseInt(st.nextToken());
                bottle[j][1] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            Queue<Loc> q = new LinkedList<>();
            q.offer(new Loc(startX, startY));

            boolean result = false;

            while(!q.isEmpty()){

                Loc loc = q.poll();

                if(Math.abs(endX - loc.x) + Math.abs(endY - loc.y) <= 1000){
                    result = true;
                    break;
                }

                for(int j = 0 ; j < n ; j++){
                    if(Math.abs(bottle[j][0] - loc.x) + Math.abs(bottle[j][1] - loc.y) <= 1000){
                        if(!check[j]){
                            check[j] = true;
                            q.offer(new Loc(bottle[j][0], bottle[j][1]));
                        }
                    }
                }
            }
            sb.append(result ? "happy" : "sad").append("\n");
        }

        System.out.println(sb);
    }
}
