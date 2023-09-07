package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2206 {
    public static class Loc{
        int x,y,check,dis;

        public Loc(int x, int y, int check, int dis) {
            this.x = x;
            this.y = y;
            this.check = check;
            this.dis = dis;
        }
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][K+1];
        for(int i = 1 ; i <= N ; i++){
            String str = br.readLine();
            for(int j = 1; j <= K ; j++){
                map[i][j] = str.charAt(j-1) - '0';
            }
        }

        boolean[][][] check = new boolean[2][N+1][K+1];

        Queue<Loc> q = new LinkedList<>();
        q.offer(new Loc(1,1,0,1));
        check[0][1][1] = true;

        int answer = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Loc loc = q.poll();

            if(loc.x == N && loc.y == K){
                answer = Math.min(answer, loc.dis);
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = loc.x + dx[i];
                int ny = loc.y + dy[i];
                if(nx > 0 && nx <= N && ny > 0 && ny <= K){
                    if(loc.check == 0){
                        if(map[nx][ny] == 1 && !check[1][nx][ny]){
                            check[1][nx][ny] = true;
                            q.offer(new Loc(nx, ny, 1,loc.dis + 1));
                        }else if(map[nx][ny] == 0 &&!check[0][nx][ny]){
                            check[0][nx][ny] = true;
                            q.offer(new Loc(nx, ny, 0, loc.dis + 1));
                        }
                    }else{
                        if(map[nx][ny] == 0 && !check[1][nx][ny]){
                            check[1][nx][ny] = true;
                            q.offer(new Loc(nx, ny, 1, loc.dis + 1));
                        }
                    }
                }
            }
        }
        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }
}
