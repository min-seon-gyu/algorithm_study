import java.io.*;
import java.util.*;

public class 백준_2573_2 {
    static int[][] arr;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int year = 0; // 년도
        while (true) {
            int iceBurgCnt = 0; // 나눠진 빙산의 개수
            visited = new boolean[n][m];
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    // 방문하지 않았고(올해에 빙산이 녹지 않았고) 빙산의 높이가 1 이상인 경우 bfs 순회
                    if (!visited[i][j] && arr[i][j] > 0) {
                        bfs(i, j);
                        iceBurgCnt++;
                    }
                }
            }

            if (iceBurgCnt == 0) {
                // 나눠진 빙산의 개수가 0개이다 => 모든 빙산이 다 녹았다
                System.out.println(0);
                return;
            }
            if (iceBurgCnt >= 2) {
                // 나눠진 빙산의 개수가 2개 이상일 때는 현재 년도 값을 출력
                System.out.println(year);
                return;
            }
            year++;
        }
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            arr[cur.x][cur.y] -= getMeltingCount(cur.x, cur.y);

            for (int i = 0; i < 4; i++) {
                int ax = cur.x + dx[i];
                int ay = cur.y + dy[i];
                if (!visited[ax][ay] && arr[ax][ay] > 0) {
                    visited[ax][ay] = true;
                    q.offer(new Point(ax, ay));
                }
            }
        }
    }

    // 녹아야 할 빙산의 높이를 반환
    public static int getMeltingCount(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int ax = x + dx[i];
            int ay = y + dy[i];
            // visited 했다는 것은 빙산이 있었던 곳
            // 올해에 빙산이 모두 녹았을 수도 있으므로, 올해에 빙산이 있었던 곳은 제외하고 계산
            if (!visited[ax][ay] && arr[ax][ay] <= 0) {
                cnt++;
            }
        }
        return cnt;
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
