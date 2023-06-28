import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_15681 {

    static int[] size;
    static int[] parent;
    static ArrayList<Integer>[] arr;

    public static void run() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];
        parent = new int[N+1];
        size = new int[N+1];

        for(int i = 1 ; i <= N ; i++){
            arr[i] = new ArrayList();
        }

        for(int i = 0 ; i < N-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        Queue<Integer> q = new LinkedList();
        q.offer(R);

        while(!q.isEmpty()){
            int data = q.poll();
            for(int value : arr[data]){
                if(value == parent[data]) continue;
                q.offer(value);
                parent[value] = data;
            }
        }

        calculate(R);

        for(int i = 0 ; i < Q ; i++){
            int data = Integer.parseInt(br.readLine());
            System.out.println(size[data]);
        }
    }

    public static void calculate(int cur) {
        size[cur] = 1;

        for(int next : arr[cur]) {
            if(next == parent[cur]) continue;
            calculate(next);
            size[cur] += size[next];
        }
    }
}
