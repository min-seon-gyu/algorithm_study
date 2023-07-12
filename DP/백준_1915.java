import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1915 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 길이 n
        int m = Integer.parseInt(st.nextToken()); // 길이 m
        int[][] map = new int[n][m]; // 전체 배열
        int[][] answer = new int[n][m]; // 누적합 배열
        int max = 0;

        //map 값 채우기
        for(int i = 0 ; i < n ; i++){
            String row = br.readLine();
            for(int j = 0 ; j < m ; j++){
                map[i][j] = row.charAt(j) - '0';
                if(map[i][j] == 1){
                    max = 1;
                }
            }
        }

        //누접합 [0][0]은 map[0][0] 그대로
        answer[0][0] = map[0][0];

        //누적합 배열 채우는 코드
        for(int i = 1; i < n ; i++){
            answer[i][0] = answer[i-1][0] + map[i][0];
        }
        for(int i = 1; i < m ; i++){
            answer[0][i] = answer[0][i-1] + map[0][i];
        }
        for(int i = 1 ; i < n ; i++){
            for(int j = 1 ; j < m ; j++){
                answer[i][j] = answer[i-1][j] + answer[i][j-1] - answer[i-1][j-1] + map[i][j];
            }
        }

        //주요 로직 코드


        //시간복잡도가 n^2
        //n, m = 1000이어도 시간복잡도는 n^2 + (n-1)^2 + (n-2)^2 ... = n^2
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){

                int length = 0;
                while(true){

                    length++;

                    int di = i+length;
                    int dj = j+length;

                    if(di < n && dj < m){
                        int sum = 0;
                        if(i != 0 && j != 0){
                            sum = answer[di][dj] - answer[i-1][dj] - answer[di][j-1] + answer[i-1][j-1];
                        }else if(j != 0){
                            sum = answer[di][dj] - answer[di][j-1];
                        }else if(i != 0){
                            sum = answer[di][dj] - answer[i-1][dj];
                        }else{
                            sum = answer[di][dj];
                        }

                        if(sum == (length + 1)*(length + 1)){
                            max = Math.max(max, sum);
                        }
                    }else{
                        break;
                    }
                }
            }
        }
        System.out.println(max);
    }
}
