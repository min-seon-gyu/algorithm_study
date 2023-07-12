import java.io.*;
import java.util.*;

// 뿌요뿌요: https://www.acmicpc.net/problem/11559
public class Baekjoon_11559 {

    char[][] board = new char[12][6];
    int[][] checked = new int[12][6];
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    int count = 0;

    public static void main(String[] args) {
        try {
            Baekjoon_11559 main = new Baekjoon_11559();
            main.run();
        } catch (IOException ignore) {
        }
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        while (true) {
            boolean isBomb = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    // 현재 값이 뿌요뿌요 블럭이고 이전에 체크하지 않았을 때
                    if (board[i][j] != '.' && checked[i][j] == 0) {
                        List<Point> puyos = getNearByPuyos(i, j);
                        if (puyos.size() >= 4) {
                            isBomb = true;
                            bombPuyos(puyos);
                        }
                    }
                }
            }
            sortDownPuyo();
            clearChecked();

            // 종료 조건: 반복문 돌 동안 한 번도 터지지 않음
            if (!isBomb) {
                System.out.println(count);
                return;
            } else {
                count++;
            }
        }
    }

    // start점을 기준으로, 상하좌우 연결된 뿌요의 위치 값 조회
    public List<Point> getNearByPuyos(int startX, int startY) {
        List<Point> result = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        Point start = new Point(startX, startY);
        queue.offer(start);
        result.add(start);
        checked[startX][startY] = 1;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ax = dx[i] + current.x;
                int ay = dy[i] + current.y;
                if (ax >= 0 && ay >= 0 && ax < 12 && ay < 6) {
                    // 이전에 방문하지 않았고 현재 뿌요가 같을 때
                    if (board[ax][ay] == board[current.x][current.y] && checked[ax][ay] == 0) {
                        Point aPoint = new Point(ax, ay);
                        result.add(aPoint);
                        queue.offer(aPoint);
                        checked[ax][ay] = 1;
                    }
                }
            }
        }
        return result;
    }

    // 전달받은 뿌요를 삭제
    public void bombPuyos(List<Point> puyos) {
        for (Point puyo : puyos) {
            board[puyo.x][puyo.y] = '.';
        }
    }

    // 뿌요 아래로 정렬
    public void sortDownPuyo() {
        for (int j = 0; j < 6; j++) {
            int idx = -1;
            for (int i = 11; i >= 0; i--) {
                if (board[i][j] == '.') {
                    if (idx == -1) {
                        idx = i;
                    }
                } else {
                    if (idx != -1) {
                        board[idx][j] = board[i][j];
                        board[i][j] = '.';
                        idx -= 1;
                    }
                }
            }
        }
    }

    public void clearChecked() {
        checked = new int[12][6];
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
