import java.util.*;
public class Kakao_1 {

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = new String[] {"A 6", "B 12", "C 3"};
        String[] aa = new String[] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        solution(today, terms, aa);
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        String[] todayArr = today.split("\\.");
        int total =
                (Integer.parseInt(todayArr[0]) * 28 * 12) +
                        (Integer.parseInt(todayArr[1]) * 28) +
                        Integer.parseInt(todayArr[2]);

        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0 ; i < terms.length ; i++){
            String[] termsArr = terms[i].split("\\s");
            String type = termsArr[0];
            String period = termsArr[1];
            map.put(type, Integer.parseInt(period));
        }

        List<Integer> lst = new ArrayList<>();

        for(int i = 0 ; i < privacies.length ; i++){
            String[] privaciesArr = privacies[i].split("\\s");
            String period = privaciesArr[0];
            String type = privaciesArr[1];

            String[] periodArr = period.split("\\.");
            int value =
                    (Integer.parseInt(periodArr[0]) * 28 * 12) +
                            (Integer.parseInt(periodArr[1]) * 28) +
                            Integer.parseInt(periodArr[2]) - 1;

            value += map.get(type) * 28;
            if(value < total){
                lst.add(i+1);
            }
        }
        return lst.stream().mapToInt(i -> i).toArray();
    }
}
