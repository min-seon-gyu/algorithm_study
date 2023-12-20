package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_15653 {

    //빨간공과 파란공을 하나로 묶는 클래스
    public static class Node{
        int blueX, blueY, redX, redY, c;

        public Node(int blueX, int blueY, int redX, int redY, int c) {
            this.blueX = blueX;
            this.blueY = blueY;
            this.redX = redX;
            this.redY = redY;
            this.c = c;
        }
    }

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];

        int rX = 0;
        int rY = 0;
        int bX = 0;
        int bY = 0;

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R'){
                    rX = i;
                    rY = j;
                    map[i][j] = '.';
                }else if(map[i][j] == 'B'){
                    bX = i;
                    bY = j;
                    map[i][j] = '.';
                }
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(bX, bY, rX, rY, 0));
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[bX][bY][rX][rY] = true;
        int answer = -1;


        while(!q.isEmpty()){
            Node node = q.poll();
            boolean check = false;
            for(int i = 0 ; i < 4 ; i++){
                int blueX = node.blueX;
                int blueY = node.blueY;
                int redX = node.redX;
                int redY = node.redY;

                boolean redEnd = false;
                boolean blueEnd = false;

                int redCount = 0;
                int blueCount = 0;

                while(blueX + dx[i] >= 0 && blueY + dy[i] >= 0 && blueX + dx[i] < N && blueY + dy[i] < M && map[blueX + dx[i]][blueY + dy[i]] != '#'){
                    if(map[blueX + dx[i]][blueY + dy[i]] == 'O'){
                        blueEnd = true;
                        break;
                    }
                    //
                    blueCount++;
                    blueX += dx[i];
                    blueY += dy[i];
                }

                while(redX + dx[i] >= 0 && redY + dy[i] >= 0 && redX + dx[i] < N && redY + dy[i] < M && map[redX + dx[i]][redY + dy[i]] != '#'){
                    if(map[redX + dx[i]][redY + dy[i]] == 'O'){
                        redEnd = true;
                        break;
                    }
                    redCount++;
                    redX += dx[i];
                    redY += dy[i];
                }

                //성공
                if(redEnd && !blueEnd){
                    answer = node.c + 1;
                    check = true;
                    break;
                }
                //실패
                if(blueEnd){
                    continue;
                }

                int nextRedX = redX;
                int nextRedY = redY;
                int nextBlueX = blueX;
                int nextBlueY = blueY;

                if(redX == blueX && redY == blueY){
                    if(redCount > blueCount){
                        if(i == 0){
                            nextRedX -= 1;
                        }else if(i == 1){
                            nextRedY -= 1;
                        }else if(i == 2){
                            nextRedX += 1;
                        }else{
                            nextRedY += 1;
                        }
                    }else{
                        if(i == 0){
                            nextBlueX -= 1;
                        }else if(i == 1){
                            nextBlueY -= 1;
                        }else if(i == 2){
                            nextBlueX += 1;
                        }else{
                            nextBlueY += 1;
                        }
                    }
                }
                if(!visited[nextBlueX][nextBlueY][nextRedX][nextRedY]){
                    q.offer(new Node(nextBlueX, nextBlueY, nextRedX, nextRedY, node.c + 1));
                    visited[nextBlueX][nextBlueY][nextRedX][nextRedY] = true;
                }
            }
            if(check) break;
        }

        System.out.println(answer);
    }
}
