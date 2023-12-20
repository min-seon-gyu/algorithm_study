package 백트랙킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2239 {
    static int[][] map = new int[9][9];
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i < 9 ; i++){
            char[] arr = br.readLine().toCharArray();
            for(int j = 0 ; j < 9 ; j++){
                map[i][j] = arr[j] - '0';
            }
        }
        go(0,0);
    }

    private static void go(int x, int y) {

        if(flag) return;

        if(y > 8){
            x++;
            y = 0;
        }

        if(x > 8){
            print();
            flag = true;
            return;
        }

        if(map[x][y] == 0){
            for(int k = 1 ; k <= 9 ; k++){
                if(check(map, x, y, k)){
                    map[x][y] = k;
                    go(x,y+1);
                    map[x][y] = 0;
                }
            }
        }else{
            go(x, y+1);
        }
    }

    private static void print() {
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean check(int[][] map, int i, int j, int value){
        //가로 체크
        for(int idx = 0 ; idx < 9 ; idx++){
            if(map[i][idx] == value) return false;
        }

        //세로 체크
        for(int idx = 0 ; idx < 9 ; idx++){
            if(map[idx][j] == value) return false;
        }

        //구역 체크
        int s = (i/3)*3;
        int e = (j/3)*3;
        for(int x = s ; x < s + 3 ; x++){
            for(int y = e ; y < e + 3 ; y++){
                if(map[x][y] == value) return false;
            }
        }

        return true;
    }
}


// int idx = (i / 3 * 3) + (j / 3);
// 0,0 0,1 0,2 1,0 1,1 1,2 2,0 2,1 2,2 = 0
// 0,3 0,4 0,5 1,3 1,4 1,5 2,3 2,4 2,5 = 1
// 0,6 0,7 0,8 1,6 1,7 1,8 2,6 2,7 2,8 = 2

// 3,0 3,1 3,2 4,0 4,1 4,2 5,0 5,1 5,2 = 3
// 3,3 3,4 3,5 4,3 4,4 4,5 5,3 5,4 5,5 = 4
// 3,6 3,7 3,8 4,6 4,7 4,8 5,6 5,7 5,8 = 5

// 6,0 6,1 6,2 7,0 7,1 7,2 8,0 8,1 8,2 = 6
// 6,3 6,4 6,5 7,3 7,4 7,5 8,3 8,4 8,5 = 7
// 6,6 6,7 6,8 7,6 7,7 7,8 8,6 8,7 8,8 = 8
