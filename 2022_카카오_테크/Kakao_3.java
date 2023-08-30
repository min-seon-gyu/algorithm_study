package 카카오_2022_테크;

public class Kakao_3 {

    public static void main(String[] args) {
        System.out.println(solution(10, 0, new int[][] {{0, 0, 0, 2, 1}, {10, 10, 0, 2, 1}}));
    }


    public static int solution(int alp, int cop, int[][] problems) {

        int maxAlp = 0;
        int maxCop = 0;

        for(int i = 0; i < problems.length ; i++){
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }

        if(alp >= maxAlp && cop >= maxCop){
            return 0;
        }

        int[][] dp = new int[maxAlp+1][maxCop+1];



        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        for(int i = alp; i <= maxAlp ; i++){
            for(int j = cop ; j <= maxCop ; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }


        dp[alp][cop] = 0;


        for(int i = alp  ; i <= maxAlp ; i++){
            for(int j = cop; j <= maxCop ; j++){

                if(i+1 <= maxAlp){
                    dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
                }

                if(j+1 <= maxCop){
                    dp[i][j+1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
                }


                for(int k = 0; k < problems.length ; k++){
                    if(i >= problems[k][0] && j >= problems[k][1]){
                        int nextAlp = Math.min(maxAlp, i + problems[k][2]);
                        int nextCop = Math.min(maxCop, j + problems[k][3]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problems[k][4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}
