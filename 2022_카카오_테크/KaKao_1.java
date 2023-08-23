package 카카오2022;

public class KaKao_1 {

    public static void main(String[] args) {

        String[] survey1 = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices1 = {5, 3, 2, 7, 5};

        String[] survey2 = {"TR", "RT", "TR"};
        int[] choices2 = {7, 1, 3};

        System.out.println(run(survey1, choices1));
        System.out.println(run(survey2, choices2));
    }

    private static String run(String[] survey, int[] choices) {
        // 'RCJA' 기준
        int[] answer = new int[4];

        for(int i = 0; i < survey.length ; i++){
            String str = survey[i];
            int point = 4 - choices[i];

            if(str.contains("R")){
                if(str.charAt(0) == 'R'){
                    answer[0] += point;
                }else{
                    answer[0] += -point;
                }
            }
            else if(str.contains("C")){
                if(str.charAt(0) == 'C'){
                    answer[1] += point;
                }else{
                    answer[1] += -point;
                }
            }
            else if(str.contains("J")){
                if(str.charAt(0) == 'J'){
                    answer[2] += point;
                }else{
                    answer[2] += -point;
                }
            }
            else if(str.contains("A")){
                if(str.charAt(0) == 'A'){
                    answer[3] += point;
                }else{
                    answer[3] += -point;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4 ; i++){
            if(i == 0){
                if(answer[i] >= 0){
                    sb.append("R");
                }else{
                    sb.append("T");
                }
            }
            else if(i == 1){
                if(answer[i] >= 0){
                    sb.append("C");
                }else{
                    sb.append("F");
                }
            }
            else if(i == 2){
                if(answer[i] >= 0){
                    sb.append("J");
                }else{
                    sb.append("M");
                }
            }
            else if(i == 3){
                if(answer[i] >= 0){
                    sb.append("A");
                }else{
                    sb.append("N");
                }
            }
        }
        return sb.toString();
    }
}
