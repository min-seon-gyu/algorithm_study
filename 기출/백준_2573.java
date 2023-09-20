package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2573 {
    public static class Loc{
        int x,y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                int value = Integer.parseInt(st.nextToken());
                if(value != 0) value++;
                map[i][j] = value;
            }
        }

        int answer = 0;
        boolean result = false;

        while(true){

            boolean exist = false;
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] != 0){
                        exist = true;
                        for(int k = 0 ; k < 4 ; k++){
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if(nx >= 0 && ny >= 0 && nx < N && ny < M){
                                if(map[nx][ny] == 0 && map[i][j] > 1){
                                    map[i][j]--;
                                }
                            }
                        }
                    }
                }
            }

            if(!exist) break;

            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] == 1) map[i][j] = 0;
                }
            }

            answer++;

            boolean[][] check = new boolean[N][M];
            Queue<Loc> q = new LinkedList<>();
            int count = 0;
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] != 0 && !check[i][j]){
                        count++;
                        check[i][j] = true;
                        q.offer(new Loc(i, j));
                        while(!q.isEmpty()){
                            Loc poll = q.poll();
                            for(int k = 0 ; k < 4 ; k++){
                                int nx = poll.x+ dx[k];
                                int ny = poll.y + dy[k];
                                if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 0){
                                    if(!check[nx][ny]){
                                        check[nx][ny] = true;
                                        q.offer(new Loc(nx, ny));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(count > 1){
                result = true;
                break;
            }
        }
        System.out.println(result ? answer : 0);
    }
}
