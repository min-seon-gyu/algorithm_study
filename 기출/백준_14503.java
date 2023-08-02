import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_14503 {


    public static class Location{
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx  = { 0, 0, -1, 1};
    static int[] dy  = { 1, -1, 0, 0};
    static int[][] map;
    static boolean[][] check;
    static int d;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        //청소 확인
        check = new boolean[N][M];

        //로봇 청소기 위치 및 방향 할당
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());


        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        Queue<Main.Location> q = new LinkedList<>();
        q.offer(new Main.Location(x,y));

        while(!q.isEmpty()){

            Main.Location poll = q.poll();
            x = poll.x;
            y = poll.y;

            //청소되지 않았으면 청소
            if(!check[x][y]){
                check[x][y] = true;
                answer++;
            }

            //근처에 청소할 곳이 없는 경우
            if(Clean(x,y)){
                // 북쪽 확인
                if(d == 0){
                    //한 칸 후진할 수 있는지 확인
                    if(x + 1 < N && map[x+1][y] == 0){
                        q.offer(new Main.Location(x+1, y));
                    }else{
                        break;
                    }
                }
                else if(d == 1){
                    //한 칸 왼쪽으로 갈 수 있는지 확인
                    if(y - 1 >= 0 && map[x][y-1] == 0){
                        q.offer(new Main.Location(x, y-1));
                    }else{
                        break;
                    }
                }
                else if(d == 2){
                    //한 칸 전진할 수 있는지 확인
                    if(x - 1 >= 0 && map[x-1][y] == 0){
                        q.offer(new Main.Location(x-1, y));
                    }else{
                        break;
                    }
                }
                else if(d == 3){
                    //한 칸 오른쪽으로 갈 수 있는지 확인
                    if(y + 1 < M && map[x][y+1] == 0){
                        q.offer(new Main.Location(x, y+1));
                    }else{
                        break;
                    }
                }
            }
            //근처에 청소할 곳이 있는 경우
            else{
                d = d - 1 >= 0 ? d - 1 : 3;
                if(d == 0){
                    //바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우
                    if(x - 1 >= 0 && map[x-1][y] == 0 && !check[x-1][y]){
                        q.offer(new Main.Location(x-1, y));
                    }else{
                        q.offer(new Main.Location(x, y));
                    }
                }
                else if(d == 1){
                    //바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우
                    if(y + 1 < M && map[x][y+1] == 0 && !check[x][y+1]){
                        q.offer(new Main.Location(x, y+1));
                    }else{
                        q.offer(new Main.Location(x, y));
                    }
                }
                else if(d == 2){
                    //바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우
                    if(x + 1 < N && map[x+1][y] == 0 && !check[x+1][y]){
                        q.offer(new Main.Location(x+1, y));
                    }else{
                        q.offer(new Main.Location(x, y));
                    }
                }
                else if(d == 3){
                    //바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우
                    if(y - 1 >= 0 && map[x][y-1] == 0 && !check[x][y-1]){
                        q.offer(new Main.Location(x, y-1));
                    }else{
                        q.offer(new Main.Location(x, y));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static boolean Clean(int x, int y){
        for(int i = 0; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                if(map[nx][ny] == 0 && !check[nx][ny]){
                    return false;
                }
            }
        }
        return true;
    }
}
