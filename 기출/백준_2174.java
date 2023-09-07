package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 백준_2174 {
    public static class Robot{
        int x,y,dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int[][] map;
    static HashMap<Integer, Robot> hashMap;
    static int A;
    static int B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B+1][A+1];

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        hashMap = new HashMap<>();

        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String type = st.nextToken();
            map[B - y + 1][x] = i;

            if(type.equals("N")){
                hashMap.put(i, new Robot(B - y + 1, x, 1));
            }else if(type.equals("E")){
                hashMap.put(i, new Robot(B - y + 1, x, 2));
            }else if(type.equals("S")){
                hashMap.put(i, new Robot(B - y + 1, x, 3));
            }else if(type.equals("W")){
                hashMap.put(i, new Robot(B - y + 1, x, 4));
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            int count = Integer.parseInt(st.nextToken());

            Robot robot = hashMap.get(number);
                if(str.equals("L") || str.equals("R")){
                    count %= 4;
                    if(str.equals("L")){
                        robot.dir -= count;
                        if(robot.dir < 1) robot.dir += 4;
                    }else{
                        robot.dir += count;
                        if(robot.dir > 4 ) robot.dir -= 4;
                    }
                    hashMap.put(number, robot);
                }
                else if(str.equals("F")){
                    int result = check(robot, number, count);
                    if(result == 0){
                        System.out.println("Robot " + number + " crashes into the wall");
                        return;
                    }else if(result > 0){
                        System.out.println("Robot " + number + " crashes into robot " + result);
                        return;
                    }
                }

        }
        System.out.println("OK");
    }

    private static int check(Robot robot, int number, int count) {
        int startX = robot.x;
        int startY = robot.y;
        int endX = 0;
        int endY = 0;

        if(robot.dir == 1){
            endX = robot.x - count;
            endY = startY;
        }else if(robot.dir == 2){
            endX = startX;
            endY = robot.y + count;
        }else if(robot.dir == 3){
            endX = robot.x + count;
            endY = startY;
        }else if(robot.dir == 4){
            endX = startX;
            endY = robot.y - count;
        }

        if(robot.dir == 1){
            for(int k = startX - 1 ; endX <= k ; k--){
                if(k < 1) return 0;
                if(map[k][endY] != 0){
                    return map[k][endY];
                }
            }
        }
        else if(robot.dir == 2){
            for(int k = startY + 1 ; endY >= k ; k++){
                if(k > A) return 0;
                if(map[endX][k] != 0){
                    return map[endX][k];
                }
            }
        }
        else if(robot.dir == 3){
            for(int k = startX + 1 ; endX >= k ; k++){
                if(k > B) return 0;
                if(map[k][endY] != 0){
                    return map[k][endY];
                }
            }
        }
        else if(robot.dir == 4){
            for(int k = startY - 1 ; endY <= k ; k--){
                if(k < 1) return 0;
                if(map[endX][k] != 0){
                    return map[endX][k];
                }
            }
        }

        map[startX][startY] = 0;
        map[endX][endY] = number;
        hashMap.put(number, new Robot(endX, endY, robot.dir));


        return -1;
    }
}
