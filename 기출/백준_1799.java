package 백트랙킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1799 {
    static int countX = 0;
    static int countY = 0;
    static boolean[] checkX;
    static boolean[] checkY;
    static int[][] chess;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chess = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int number = 2 * N - 1;


        // x + y
        checkX = new boolean[number];
        // x - y + N - 1
        checkY = new boolean[number];

        black(0,0,0);
        white(0,1,0);

        System.out.println(countX + countY);
    }



    private static void black(int x, int y, int count) {
        countX = Math.max(count, countX);

        if(y > N - 1){
            x++;
            y = x % 2 == 1 ? 1 : 0;
        }

        if(x > N - 1) return;

        if(chess[x][y] == 1 && !checkX[x+y] && !checkY[x-y+N-1]){
            checkX[x+y] = true;
            checkY[x-y+N-1] = true;
            black(x, y + 2, count + 1);
            checkX[x+y] = false;
            checkY[x-y+N-1] = false;
        }


        black(x, y + 2, count);
    }
    private static void white(int x, int y, int count) {
        countY = Math.max(count, countY);

        if(y > N - 1){
            x++;
            y = x % 2 == 1 ? 0 : 1;
        }

        if(x > N - 1) return;

        if(chess[x][y] == 1 && !checkX[x+y] && !checkY[x-y+N-1]){
            checkX[x+y] = true;
            checkY[x-y+N-1] = true;
            white(x, y + 2, count + 1);
            checkX[x+y] = false;
            checkY[x-y+N-1] = false;
        }


        white(x, y + 2, count);
    }
}
