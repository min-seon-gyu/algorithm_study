package 재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 백준_14891 {

    static LinkedList<Integer>[] arr;
    static boolean[] check;

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new LinkedList[5];

        for(int i = 1 ; i <= 4 ; i++){
            String str = br.readLine();
            arr[i] = new LinkedList();
            for(int j = 0; j < str.length() ; j++ ){
                char data = str.charAt(j);
                arr[i].offer(data - '0');
            }
        }

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){
            check = new boolean[5];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            Calculate(target, dir);
        }

        int first = arr[1].poll() == 1 ? 1 : 0;
        int second = arr[2].poll() == 1 ? 2 : 0;
        int third = arr[3].poll() == 1 ? 4 : 0;
        int fourth = arr[4].poll() == 1 ? 8 : 0;
        System.out.println(first + second + third + fourth);
    }

    private static void Calculate(int target, int dir) {

        check[target] = true;
        int beforeIndex = -1;
        int afterIndex = 1;

        if(target + beforeIndex > 0 && !check[target + beforeIndex]){
            int before = target + beforeIndex;
            if(arr[target].get(6) != arr[before].get(2)){
                Calculate(before, -dir);
            }
        }

        if(target + afterIndex <= 4 && !check[target + afterIndex]){
            int after = target + afterIndex;
            if(arr[target].get(2) != arr[after].get(6)){
                Calculate(after, -dir);
            }
        }

        if(dir == 1){
            int data = arr[target].pollLast();
            arr[target].offerFirst(data);
        }else{
            int data = arr[target].pollFirst();
            arr[target].offerLast(data);
        }
    }
}
