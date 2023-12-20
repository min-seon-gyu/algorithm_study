package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_14923 {
    public static class Location{
        int x,y,d,c;

        public Location(int x, int y, int d, int c) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.c = c;
        }
    }

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //미로의 가로, 세로 길이
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        //출발지 초기화, 위치가 배열 인덱스 기준으로 +1 이기 때문에 -1 처리
        int Hx = Integer.parseInt(st.nextToken()) - 1;
        int Hy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        //목적지 초기화, 위치가 배열 인덱스 기준으로 +1 이기 때문에 -1 처리
        int Ex = Integer.parseInt(st.nextToken()) - 1;
        int Ey = Integer.parseInt(st.nextToken()) - 1;

        int[][] map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Location> q = new LinkedList<>();
        //벽을 부순 횟수를 저장하기 위한 3차원 배열, (N,M) 위치에 도달했을 기준으로 벽을 부셨으면 1 ELSE 0
        boolean[][][] visited = new boolean[N][M][2];
        //현재위치, 움직인 거리, 벽을 부순 횟수
        q.offer(new Location(Hx, Hy, 0, 0));
        visited[Hx][Hy][0] = true;
        int answer = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Location loc = q.poll();
            //도착지에 왔으면 반복문 종료
            if(loc.x == Ex && loc.y == Ey){
                answer = loc.d;
                break;
            }else{
                for(int i = 0 ; i < 4 ; i++){
                    int nx = loc.x + dx[i];
                    int ny = loc.y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < N && ny < M){
                        //아직 한 번도 벽을 부수지 않았을 때
                        if(loc.c == 0){
                            // 벽이 없는 지형일 경우 && 벽을 부시지 않은 상태로 방문했는지
                            if(map[nx][ny] == 0 && !visited[nx][ny][0]){
                                visited[nx][ny][0] = true;
                                q.offer(new Location(nx, ny, loc.d + 1, 0));
                            }
                            // 벽이 있는 지형일 경우 && 벽을 한 번 부신 상태로 방문했는지
                            else if(map[nx][ny] == 1 && !visited[nx][ny][1]){
                                visited[nx][ny][1] = true;
                                q.offer(new Location(nx, ny, loc.d + 1, 1));
                            }
                        }
                        //한 번 벽을 부쉈을 때
                        else if(loc.c == 1){
                            // 벽이 없는 지형일 경우 && 벽을 한 번 부신 상태로 방문했는지
                            if(map[nx][ny] == 0 && !visited[nx][ny][1]){
                                visited[nx][ny][1] = true;
                                q.offer(new Location(nx, ny, loc.d + 1, 1));
                            }
                        }
                    }
                }
            }

        }

        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }
}
