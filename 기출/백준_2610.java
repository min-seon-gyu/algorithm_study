package 기타;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2610 {
    public static class Node{
        int idx, c;

        public Node(int idx, int c) {
            this.idx = idx;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] lst = new ArrayList[N+1];
        ArrayList<Integer>[] group = new ArrayList[N+1];

        for(int i = 1 ; i <= N ; i++){
            lst[i] = new ArrayList<>();
            group[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());

            lst[idx1].add(idx2);
            lst[idx2].add(idx1);
        }

        boolean[] visited = new boolean[N+1];
        int groupNumber = 0;

        for(int i = 1 ; i <= N ; i++){
            if(!visited[i]){
                groupNumber++;
                group[groupNumber].add(i);
                visited[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while(!q.isEmpty()){
                    Integer idx = q.poll();
                    for(int next : lst[idx]){
                        if(!visited[next]){
                            visited[next] = true;
                            q.offer(next);
                            group[groupNumber].add(next);
                        }
                    }
                }
            }
        }

        for(int i = 1 ; i <= groupNumber ; i++){
            int count = Integer.MAX_VALUE;
            int number = 0;
            for(int j = 0 ; j < group[i].size(); j++){
                int dis = 0;
                visited = new boolean[N+1];
                int startIdx = group[i].get(j);
                visited[startIdx] = true;
                Queue<Node> q = new LinkedList<>();
                q.offer(new Node(startIdx, 0));

                while(!q.isEmpty()){
                    Node n = q.poll();
                    dis = n.c;
                    for(int next : lst[n.idx]){
                        if(!visited[next]){
                            visited[next] = true;
                            q.offer(new Node(next, n.c + 1));
                        }
                    }
                }


                if(dis < count){
                    count = dis;
                    number = startIdx;
                }
            }
            pq.offer(number);
        }


        sb.append(groupNumber).append("\n");
        while(!pq.isEmpty()){
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb);
    }
}
