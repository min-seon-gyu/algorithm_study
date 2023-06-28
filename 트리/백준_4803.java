import java.io.*;
import java.util.*;

public class 백준_4803 {

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //
        int T = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0){
                break;
            }

            ArrayList<Integer>[] lst = new ArrayList[N+1];
            boolean[] check = new boolean[N+1];
            int[] parent = new int[N+1];

            for(int i = 1 ; i <= N ; i++){
                lst[i] = new ArrayList();
            }

            for(int i = 0; i < M ; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                lst[a].add(b);
                lst[b].add(a);
            }

            Queue<Integer> q = new LinkedList();

            int count = 0;
            for(int i = 1 ; i <=N ; i++){
                if(!check[i]){
                    check[i] = true;
                    count++;
                    q.offer(i);
                    while(!q.isEmpty()){
                        int data = q.poll();
                        for(int value : lst[data]){
                            if(value == parent[data]) continue;
                            if(!check[value]){
                                parent[value] = data;
                                check[value] = true;
                                q.offer(value);
                            }else{
                                count--;
                                q.clear();
                                break;

                            }
                        }
                    }
                }
            }

            if(count == 0) {
                sb.append("Case "+T+": No trees.").append("\n");
            }else if(count == 1){
                sb.append("Case "+T+": There is one tree.").append("\n");
            }else{
                sb.append("Case "+T+": A forest of "+count+" trees.").append("\n");
            }
            T++;
        }
        System.out.println(sb);
    }
}
