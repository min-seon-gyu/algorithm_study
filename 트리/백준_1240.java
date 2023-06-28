import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1240 {
    static ArrayList<Node>[] arr;
    static boolean[] check;
    static int N;

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];


        for(int i = 1 ; i <= N ; i++){
            arr[i] = new ArrayList();
        }

        for(int i = 0 ; i < N-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            arr[a].add(new Node(b, m));
            arr[b].add(new Node(a, m));
        }


        for(int i = 0; i < M ;i++){
            st = new StringTokenizer(br.readLine());
            sb.append(calculate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");

        }
        System.out.println(sb);
    }

    public static class Node{
        int index;
        int sum;

        public Node(int index, int sum){
            this.index=index;
            this.sum =sum;
        }
    }

    private static int calculate(int start, int end) {
        check = new boolean[N+1];
        Queue<Node> q = new LinkedList();
        check[start] = true;
        q.offer(new Node(start, 0));
        int sum = 0;
        while(!q.isEmpty()){
            Node node = q.poll();
            if(node.index == end){
                sum = node.sum;
                break;
            }
            for(Node data : arr[node.index]){
                if(check[data.index]) continue;
                q.offer(new Node(data.index, node.sum + data.sum));
                check[data.index] = true;

            }
        }
        return sum;
    }
}
