package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_25682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // K 가 1이라면 바로 리턴 0
        if(K == 1){
            System.out.println(0);
            return;
        }

        // 주어진 보드를 나타내는 배열
        boolean[][] map = new boolean[N][M];
        //좌상단이 검은색인 체스판
        boolean[][] blackMap = new boolean[N][M];
        //좌상단이 하얀색인 체스판
        boolean[][] whiteMap = new boolean[N][M];

        //보드값을 B,W에 따라 할당
        for(int i = 0 ; i < N ; i++){
            char[] arr = br.readLine().toCharArray();
            for(int j = 0 ; j < arr.length ; j++){
                if(arr[j] == 'W'){
                    map[i][j] = true;
                }
            }
        }

        //검은색인 체스판, 하얀색인 체스판 만들기
        boolean check = true;
        for(int i = 0 ; i < N ; i++){
            boolean type = check;
            for(int j = 0 ; j < M ; j++){
                blackMap[i][j] = type;
                whiteMap[i][j] = !type;
                type = !type;
            }
            check = !check;
        }

        int[][] blackSum = new int[N+1][M+1];
        int[][] whiteSum = new int[N+1][M+1];

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                //체스판과 비교하여 값이 다르면 1을 할당
                if(blackMap[i][j] != map[i][j]) blackSum[i+1][j+1] = 1;
                if(whiteMap[i][j] != map[i][j]) whiteSum[i+1][j+1] = 1;
            }
        }

        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= M ; j++){
                //누적합 코드
                blackSum[i][j] = blackSum[i][j] + blackSum[i-1][j] + blackSum[i][j-1] - blackSum[i-1][j-1];
                whiteSum[i][j] = whiteSum[i][j] + whiteSum[i-1][j] + whiteSum[i][j-1] - whiteSum[i-1][j-1];
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= M ; j++){
                int endI = i + K - 1;
                int endJ = j + K - 1;
                if(endI <= N && endJ <= M){
                    int black = blackSum[endI][endJ] + blackSum[i-1][j-1] - blackSum[i-1][endJ] - blackSum[endI][j-1];
                    int white = whiteSum[endI][endJ] + whiteSum[i-1][j-1] - whiteSum[i-1][endJ] - whiteSum[endI][j-1];
                    answer = Math.min(answer, Math.min(black, white));
                }
            }
        }

        System.out.println(answer);
    }
}
