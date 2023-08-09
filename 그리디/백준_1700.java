package codingtest;

import java.io.*;
import java.util.*;

public class Baekjoon_1700 {
    static int[] arr;
    static Set<Integer> holes = new HashSet<>();
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for (int i = 0; i < k; i++) {
            // 멀티 탭에 꽂혀있지 않은 플러그이고, 모든 멀티탭 구멍에 플러그가 꽂혀있을 때
            if (!holes.contains(arr[i]) && holes.size() >= n) {
                count++;
                holes.remove(getUnplugValue(i));
            }
            holes.add(arr[i]);
        }
        System.out.println(count);
    }

    // 빼야하는 플러그의 값을 반환하는 함수
    // 빼는 기준: (1) 이후에 아예 안 쓰는 기기, (2) 가장 나중에 쓰이는 기기
    public static int getUnplugValue(int startIdx) {
        int unplugIdx = -1; // 빼야하는 플러그의 index
        for (int plug : holes) {
            boolean isContains = false; // 이후에 사용되는지 확인

            for (int i = startIdx + 1; i < k; i++) {
                // 현재 플러그가 이후에도 사용이 된다면,
                if (plug == arr[i]) {
                    // 현재 플러그가 가장 나중에 사용이 된다면, 뽑아야 할 플러그로 저장
                    unplugIdx = Math.max(unplugIdx, i);
                    isContains = true;
                    break;
                }
            }

            // 이후에 사용되지 않는다면 빼야하는 플러그로 현재 플러그를 반환함
            if (!isContains) return plug;
        }
        return arr[unplugIdx];
    }
}