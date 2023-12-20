package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_10711 {
    public static class Wave{
        int x,y;

        public Wave(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {0,0,1,-1,-1,-1,1,1};
    static int[] dy = {1,-1,0,0,1,-1,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Wave> q = new LinkedList<>();

        int[][] map = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                if(str.charAt(j) == '.'){
                    q.offer(new Wave(i,j));
                    map[i][j] = 0;
                }else{
                    map[i][j] = str.charAt(j) - '0';
                }
            }
        }

        int answer = 0;
        while(!q.isEmpty()){
            int range = q.size();
            for(int i = 0 ; i < range ; i++){
                Wave wave = q.poll();
                for(int k = 0 ; k < 8 ; k++){
                    int nx = wave.x + dx[k];
                    int ny = wave.y + dy[k];
                    if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 0){
                        map[nx][ny]--;
                        if(map[nx][ny] == 0){
                            q.offer(new Wave(nx, ny));
                        }
                    }
                }
            }
            answer++;
        }

        System.out.println(answer-1);
    }
}
