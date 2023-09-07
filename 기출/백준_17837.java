package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_17837 {
    public static class Loc{
        int x,y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int[][] map;
    static int[] dirs;
    static LinkedList<Integer>[][] units;
    static HashMap<Integer, Loc> locMap;
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //영역의 색상을 저장하는 배열
        map = new int[N+1][N+1];
        //각 위치에 존재하는 말을 저장하는 배열
        units = new LinkedList[N+1][N+1];
        //각 말 마다 방향을 저장하는 배열
        dirs = new int[K+1];
        //각 말 마다 위치를 저장하는 맵
        locMap = new HashMap<>();

        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                units[i][j] = new LinkedList<>();
            }
        }

        for(int i = 1 ; i <= K ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            units[x][y].add(i);
            dirs[i] = dir;
            locMap.put(i, new Loc(x, y));
        }

        int answer = 1;
        while(true){
            if(answer == 1001){
                System.out.println(-1);
                return;
            }

            for(int i = 1 ; i <= K ; i++){
                Loc loc = locMap.get(i);
                if(move(i, loc.x, loc.y, dirs[i], 1)){
                    System.out.println(answer);
                    return;
                }
            }
            answer++;
        }
    }
    public static boolean move(int number, int x, int y, int dir, int count){

        if(count > 2){
            return false;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx > 0 && nx <= N && ny > 0 && ny <= N){
            if(map[nx][ny] == 0 || map[nx][ny] == 1){

                int index = units[x][y].indexOf(number);
                int size = units[x][y].size() - index;

                if(map[nx][ny] == 0){
                    for(int i = 0 ; i < size ; i++){
                        int value = units[x][y].remove(index);
                        units[nx][ny].add(value);
                        locMap.put(value, new Loc(nx, ny));
                    }
                }else{
                    for(int i = 0 ; i < size ; i++){
                        int value = units[x][y].removeLast();
                        units[nx][ny].add(value);
                        locMap.put(value, new Loc(nx, ny));
                    }
                }
            }
            else{
                if(dir == 1){
                    dirs[number] = 2;
                    int nx1 = x + dx[dirs[number]];
                    int ny1 = y + dy[dirs[number]];
                    if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                        return move(number, x, y, dirs[number], count + 1);
                    }
                }else if(dir == 2){
                    dirs[number] = 1;
                    int nx1 = x + dx[dirs[number]];
                    int ny1 = y + dy[dirs[number]];
                    if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                        return move(number, x, y, dirs[number], count + 1);
                    }
                }else if(dir == 3){
                    dirs[number] = 4;
                    int nx1 = x + dx[dirs[number]];
                    int ny1 = y + dy[dirs[number]];
                    if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                        return move(number, x, y, dirs[number], count + 1);
                    }
                }else if(dir == 4){
                    dirs[number] = 3;
                    int nx1 = x + dx[dirs[number]];
                    int ny1 = y + dy[dirs[number]];
                    if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                        return move(number, x, y, dirs[number], count + 1);
                    }
                }
            }
            return units[nx][ny].size() >= 4;
        }else{
            if(dir == 1){
                dirs[number] = 2;
                int nx1 = x + dx[dirs[number]];
                int ny1 = y + dy[dirs[number]];
                if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                    return move(number, x, y, dirs[number], count + 1);
                }
            }else if(dir == 2){
                dirs[number] = 1;
                int nx1 = x + dx[dirs[number]];
                int ny1 = y + dy[dirs[number]];
                if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                    return move(number, x, y, dirs[number], count + 1);
                }
            }else if(dir == 3){
                dirs[number] = 4;
                int nx1 = x + dx[dirs[number]];
                int ny1 = y + dy[dirs[number]];
                if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                    return move(number, x, y, dirs[number], count + 1);
                }
            }else if(dir == 4){
                dirs[number] = 3;
                int nx1 = x + dx[dirs[number]];
                int ny1 = y + dy[dirs[number]];
                if(nx1 > 0 && nx1 <= N && ny1 > 0 && ny1 <= N && map[nx1][ny1] != 2) {
                    return move(number, x, y, dirs[number], count + 1);
                }
            }
            return false;
        }
    }
}
