import java.io.*;
import java.util.*;

public class Baekjoon_14891 {

    int[][] wheels = new int[4][8];

    public static void main(String[] args) {
        try {
            Baekjoon_14891 main = new Baekjoon_14891();
            main.run();
        } catch (IOException ignore) {
        }
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) { // 데이터 초기화
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = line.charAt(j) - '0';
            }
        }
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rotate(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())); // 회전
        }
        br.close();
        System.out.println(calculateScore()); // 결과값 출력
    }

    public void rotate(int wheelIdx, int direction) {
        List<int[]> pairs = new ArrayList<>();
        pairs.add(new int[]{wheelIdx, direction});

        int idx = wheelIdx;
        int dir = direction;

        for (int prev = wheelIdx - 1; prev >= 0; prev--) { // 이전 값 검사
            if (wheels[idx][6] != wheels[prev][2]) {
                idx = prev;
                dir *= -1;
                pairs.add(new int[]{idx, dir});
            } else {
                break;
            }
        }
        idx = wheelIdx;
        dir = direction;
        for (int next = wheelIdx + 1; next < 4; next++) { // 다음 값 검사
            if (wheels[idx][2] != wheels[next][6]) {
                idx = next;
                dir *= -1;
                pairs.add(new int[]{idx, dir});
            } else {
                break;
            }
        }

        for (int[] result : pairs) {
            sort(result[0], result[1]);
        }
    }

    public int calculateScore() { // 점수 계산
        return wheels[0][0] + wheels[1][0] * 2 + wheels[2][0] * 4 + wheels[3][0] * 8;
    }

    private void sort(int wheelIdx, int direction) {
        int[] sorted = new int[8];
        if (direction == 1) {
            sorted[0] = wheels[wheelIdx][7];
            System.arraycopy(wheels[wheelIdx], 0, sorted, 1, 7);
        } else {
            sorted[7] = wheels[wheelIdx][0];
            System.arraycopy(wheels[wheelIdx], 1, sorted, 0, 7);
        }

        wheels[wheelIdx] = sorted;
    }
}