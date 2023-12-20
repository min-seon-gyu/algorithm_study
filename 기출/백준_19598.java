package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_19598 {
    public static class Time implements Comparable<Time>{
        int s,e;

        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Time t){
            if(this.s == t.s){
                return this.e - t.e;
            }
            return this.s - t.s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Time> pq = new PriorityQueue<>();

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new Time(start, end));
        }

        int answer = 1;
        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        endTime.offer(0);

        while(!pq.isEmpty()){
            Time poll = pq.poll();

            if(endTime.peek() <= poll.s){
                endTime.poll();
                endTime.offer(poll.e);
            }else{
                answer++;
                endTime.offer(poll.e);
            }
        }
        System.out.println(answer);
    }
}
