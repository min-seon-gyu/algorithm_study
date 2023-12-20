package 스위핑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_2836 {
    public static class Taxi implements Comparable<Taxi>{
        int s,e;

        public Taxi(int s, int e) {
            this.s = s;
            this.e = e;
        }


        @Override
        public int compareTo(Taxi o) {
            if(this.s == o.s){
                //종료점 오름차순
                return this.e - o.e;
            }
            //시작점 내림차순
            return o.s - this.s;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Taxi> lst = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (s > e) {
                lst.add(new Taxi(s, e));
            }
        }

        if (lst.size() == 0) {
            System.out.println(M);
            return;
        }

        Collections.sort(lst);

        long answer = M;
        int s = lst.get(0).s;
        int e = lst.get(0).e;
        ;
        for (int i = 1; i < lst.size(); i++) {
            //출발지가 같으면 Continue
            if (s == lst.get(i).s) continue;

            //새로운 출발지가 기존 도착지보다 클 경우
            if (lst.get(i).s >= e) {
                e = Math.min(lst.get(i).e, e);
            }

            //새로운 출발지가 기존 도착지보다 작을 경우
            else if (lst.get(i).s < e) {
                answer += (s - e) * 2;
                s = lst.get(i).s;
                e = lst.get(i).e;
            }
        }
        System.out.println(answer + (s - e) * 2);
    }
}
