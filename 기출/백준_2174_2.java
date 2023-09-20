import java.util.*;
import java.io.*;

public class 백준_2174_2 {
    static List<Robot> robots = new ArrayList<>(); // 로봇의 정보를 저장
    static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 로봇 초기화 부분
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            robots.add(new Robot(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        // 명령어 실행 부분
        try {
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                Robot robot = robots.get(Integer.parseInt(st.nextToken()) - 1);
                robot.executeOperand(st.nextToken(), Integer.parseInt(st.nextToken()));
            }
        } catch (Exception e) {
            // 오류(벽/로봇에 부딪힘)가 발생하면 메세지 출력
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("OK");
    }

    static class Robot {
        int id, x, y, dir;

        // 각각 방향이 [동, 남, 서, 북]으로 이동했을 때의 좌표 값
        private int[] dx = {1,0,-1,0};
        private int[] dy = {0,-1,0,1};

        public Robot(int id, int x, int y, String direction) {
            this.id = id;
            this.x = x;
            this.y = y;

            switch (direction) {
                case "E":
                    dir = 0;
                    break;
                case "S":
                    dir = 1;
                    break;
                case "W":
                    dir = 2;
                    break;
                default:
                    dir = 3;
            }
        }

        public void executeOperand(String operand, int count) throws Exception {
            switch (operand) {
                case "F":
                    moveFront(count);
                    break;
                case "L":
                    rotateLeft(count);
                    break;
                default:
                    rotateRight(count);
            }
        }

        private void moveFront(int count) throws Exception {
            for (int i = 0; i < count; i++) {
                x += dx[dir];
                y += dy[dir];

                // 벽에 부딪혔는지 확인
                // 로봇은 1부터 시작하므로 x, y가 각각 0일 때 벽이라고 가정함
                if (x == 0 || y == 0 || x > a || y > b) {
                    throw new Exception("Robot " + id + " crashes into the wall");
                }

                // 로봇에 부딪혔는지 확인
                // 이동한 위치에 다른 로봇이 존재하는지 확인함
                for (Robot robot : robots) {
                    if (robot.id == this.id) continue;
                    if (robot.x == this.x && robot.y == this.y) {
                        throw new Exception("Robot " + this.id + " crashes into robot " + robot.id);
                    }
                }
            }
        }

        private void rotateRight(int count) {
            for (int i = 0; i < count; i++) {
                dir++;
                if (dir == 4) dir -=4;
            }
        }

        private void rotateLeft(int count) {
            for (int i = 0; i < count; i++) {
                dir--;
                if (dir == -1) dir += 4;
            }
        }
    }
}