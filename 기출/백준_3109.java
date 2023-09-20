package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_3109 {
    public static class Loc{
        int x,y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean[][] check;
    static char[][] map;
    static int R;
    static int C;
    static int[] dx = {-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        check = new boolean[R][C];

        for(int i = 0 ; i < R ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < C ; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int answer = 0;
        for(int i = 0 ; i < R ; i++){
            answer += dfs(i, 0);
        }
        System.out.println(answer);
    }

    private static int dfs(int x, int y) {

        if(y == C - 1){
            return 1;
        }

        for(int i = 0 ; i < 3 ; i++){
            int nx = x + dx[i];
            int ny = y + 1;
            if(nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != 'x'){
                if(!check[nx][ny]){
                    check[nx][ny] = true;
                    if(dfs(nx, ny) == 1){
                        return 1;
                    }
                }
            }
        }

        return 0;
    }
}
