package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int max = 0;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        int answerTime = Integer.MAX_VALUE;
        int answerHeight = 0;

        // 500 * 500 * 257 = 64,250,000
        for(int i = 0 ; i <= max ; i++){
            int count = B;
            int time = 0;

            for(int j = 0 ; j < N ; j++){
                for(int k = 0 ; k < M ; k++){
                    if(map[j][k] == i) continue;

                    if(map[j][k] < i){
                        int value = i - map[j][k];
                        time += value;
                        count -= value;
                    }else{
                        int value = map[j][k] - i;
                        time += (value*2);
                        count += value;
                    }
                }
            }

            if(count >= 0 && answerTime >= time){
                answerTime = time;
                answerHeight = i;
            }
        }

        System.out.println(answerTime + " " + answerHeight);



    }

}
