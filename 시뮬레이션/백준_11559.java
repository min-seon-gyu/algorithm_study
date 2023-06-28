package 재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_11559 {

    public static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = { 0, 0, 1, -1};
    static int[] dy = { 1, -1, 0, 0};
    static char[][] map;

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];

        for(int i = 0; i < 12 ; i++){
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        while(Calculate()){
            count++;
            fill();
        }
        System.out.print(count);
    }

    private static void swap(int row1, int row2, int col){
        char tmp = map[row1][col];
        map[row1][col] = map[row2][col];
        map[row2][col] = tmp;
    }

    private static void fill() {

        for(int i = 10 ; i >= 0 ; i--){
            for(int j = 0; j < 6 ; j++){
                if(map[i][j] != '.'){
                    int row = i + 1;
                    int target = 0;
                    while(row < 12){
                        if(map[row][j] != '.'){
                            break;
                        }else{
                            target = row;
                            row++;
                        }
                    }
                    if(target != 0){
                        swap(i, target, j);
                    }
                }
            }
        }
    }

    private static boolean Calculate() {

        boolean result = false;

        boolean[][] check = new boolean[12][6];
        Queue<Point> q = new LinkedList();
        ArrayList<Point> lst = new ArrayList<>();

        for(int i = 0; i < 12 ; i++){
            for(int j = 0; j < 6 ; j++){
                if(!check[i][j] && map[i][j] != '.'){
                    check[i][j] = true;
                    lst.add(new Point(i, j));
                    q.offer(new Point(i, j));
                    while(!q.isEmpty()){
                        Point p = q.poll();
                        int x = p.x;
                        int y = p.y;
                        for(int k = 0 ; k < 4 ; k++){
                            int nx  = x + dx[k];
                            int ny  = y + dy[k];
                            if(nx >= 0 && ny >= 0 && nx < 12 && ny < 6 && !check[nx][ny]){
                                if(map[nx][ny] == map[i][j]){
                                    check[nx][ny] = true;
                                    lst.add(new Point(nx, ny));
                                    q.offer(new Point(nx, ny));
                                }
                            }
                        }
                    }
                    if(lst.size() >= 4){
                        result = true;
                        for(Point p : lst){
                            map[p.x][p.y] = '.';
                        }
                    }
                    lst.clear();
                }
            }
        }
        return result;
    }
}
