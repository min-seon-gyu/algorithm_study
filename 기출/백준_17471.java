package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_17471 {
    static int N;
    static int[] countArr;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전체 지역구 수
        N = Integer.parseInt(br.readLine());
        // 지역구 별 사람 수
        countArr = new int[N+1];
        // 지역구 별 인접한 지역구
        arr = new ArrayList[N+1];

        // 지역구 별 사람 수 할당
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++){
            countArr[i] = Integer.parseInt(st.nextToken());
        }

        // 지역구 별 인접한 지역구를 리스트에 추가
        for(int i = 1 ; i <= N ; i++){
            arr[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            if(count > 0){
                for(int j = 0 ; j < count ; j++){
                    arr[i].add(Integer.parseInt(st.nextToken()));
                }
            }
        }

        // 지역구 조합 최대값
        int state = (1 << N) - 1;
        // 출력값
        int answer = Integer.MAX_VALUE;

        // 지역구 조합을 2개로 나누는 최소값 1부터 최대값 state - 1 까지 반복문 실행
        for(int i = 1 ; i < state ; i++){
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            // 조합을 비트계산을 통해 A, B 리스트에 추가
            for(int j = 1 ; j <= N ; j++){
                if((i & (1 << (j-1))) > 0){
                    groupA.add(j);
                }else{
                    groupB.add(j);
                }
            }

            // 각 조합끼리 서로 인접하는지 확인
            if(checkGroup(groupA) && checkGroup(groupB)){
                // 두 조합의 차이를 answer와 비교해서 갱신
                answer = Math.min(answer, Math.abs(getCount(groupA) - getCount(groupB)));
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static int getCount(List<Integer> group) {
        int sum = 0;
        // 현재 그룹에 포함되어져있는 지역구의 사람수 총합
        for(int idx : group){
            sum += countArr[idx];
        }
        return sum;
    }

    private static boolean checkGroup(List<Integer> group) {
        boolean result = true;

        // 방문배열
        boolean[] check = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        // 임의의 값을 q에 추가하고 방문체크
        q.offer(group.get(0));
        check[group.get(0)] = true;

        while(!q.isEmpty()){
            int idx = q.poll();
            for(int next : arr[idx]){
                // 인접한 지역구가 아직 방문하지 않았고, 해당 그룹에 포함되어있으면 실행
                if(!check[next] && group.contains(next)){
                    check[next] = true;
                    q.offer(next);
                }
            }
        }

        // 해당 그룹에 속한 지역구 중 하나라도 방문하지 않았다면 false
        for(int idx : group){
            if(!check[idx]) result = false;
        }

        return result;
    }
}
