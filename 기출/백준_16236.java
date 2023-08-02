import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_16236 {

    public static class Location{
        int x;
        int y;
        int count;
        int distance;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Location(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static int[] dx = { 0, 0, -1, 1};
    static int[] dy = { 1, -1, 0, 0};
    static int[][] map;
    static int T;
    //아기 상어 크기
    static int size = 2;
    //아기 상어 먹은 물고기 수
    static int eatCount = 0;
    //아기 상어 x 위치
    static int cx;
    //아기 상어 y 위치
    static int cy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        map = new int[T][T];
        int answer = 0;

        for(int i = 0 ; i < T ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < T ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    map[i][j] = 0;
                    cx = i;
                    cy = j;
                }
            }
        }

        while(check()){
            List<Location> list = getList();
            //먹을 수 있는 물고기가 1개 일 때
            if(list.size() == 1){
                Location location = list.get(0);
                //먹으러 갈 수 있는 지 유무
                if(distance(location.x, location.y) != Integer.MAX_VALUE){
                    answer += distance(location.x, location.y);
                    eat(location.x, location.y);
                }else{
                    break;
                }
            }else{
                //거리 순, x가 작을 수록, y가 작을 수록 순으로 정렬
                Collections.sort(list, new Comparator<Location>() {
                    @Override
                    public int compare(Location a, Location b){
                        a.distance = distance(a.x,a.y);
                        b.distance = distance(b.x,b.y);

                        if(a.distance > b.distance){
                            return 1;
                        }
                        else if(a.distance < b.distance){
                            return -1;
                        }
                        else{
                            if(a.x > b.x){
                                return 1;
                            }
                            else if(a.x < b.x){
                                return -1;
                            }
                            else{
                                if(a.y > b.y){
                                    return 1;
                                }
                                else{
                                    return -1;
                                }
                            }
                        }
                    }
                });
                Location location = list.get(0);
                //먹으러 갈 수 있는 지 유무
                if(location.distance != Integer.MAX_VALUE){
                    answer += location.distance;
                    eat(location.x, location.y);
                }else {
                    break;
                }
            }
        }
        System.out.println(answer);
    }



    //상어로 부터 먹이까지 거리 구하기
    private static int distance(int tx, int ty){
        boolean[][] check = new boolean[T][T];
        check[cx][cy] = true;
        Queue<Location> q = new LinkedList<>();
        int answer = 0;

        q.offer(new Location(cx, cy, 0));

        while(!q.isEmpty()){

            Location poll = q.poll();
            int x = poll.x;
            int y = poll.y;
            int count = poll.count;

            if(x == tx && y == ty){
                answer = count;
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < T && ny < T){
                    if(map[nx][ny] <= size && !check[nx][ny]){
                        q.offer(new Location(nx, ny, count+1));
                        check[nx][ny] = true;
                    }
                }
            }
        }
        //먹으러 갈 수 가 없으면 Integer.MAX_VALUE 값 할당
        return answer != 0 ? answer : Integer.MAX_VALUE;
    }

    //자신의 크기보다 작은 물고기 존재 유무 반환
    private static boolean check() {
        for(int i = 0; i < map.length ; i++){
            for(int j = 0; j < map[i].length ; j++){
                if(map[i][j] >= 1 && map[i][j] < size){
                    return true;
                }
            }
        }
        return false;
    }

    //자신의 크기보다 작은 물고기 리스트 반환
    private static List<Location> getList(){
        List<Location> result = new ArrayList<>();
        for(int i = 0; i < map.length ; i++){
            for(int j = 0; j < map[i].length ; j++){
                if(map[i][j] >= 1 && map[i][j] < size){
                    result.add(new Location(i,j));
                }
            }
        }
        return result;
    }

    // 물고기를 먹고 나서 값 처리
    private static void eat(int tx, int ty) {
        map[tx][ty] = 0;
        cx = tx;
        cy = ty;
        eatCount++;
        if(eatCount == size){
            size++;
            eatCount = 0;
        }
    }
}
