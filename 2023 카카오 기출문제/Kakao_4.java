package 카카오2023;

import java.util.*;

public class Kakao_4 {

    static int height = 1;

    public static void main(String[] args) {
        int[] solution = solution(new long[]{1});
        for(int data : solution){
            System.out.print(data);
        }
    }

    public static int[] solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();

        for(int i = 0; i < numbers.length ; i++){
            long number = numbers[i];
            String str = getBinary(number);
            int result = check(getBinary(number), str.length() / 2, height);
            answer.add(result);
        }

        return answer.stream().mapToInt(i ->i).toArray();
    }

    private static int check(String str, int center, int h) {
        if(h < 2){
            return 1;
        }

        int data = (int)Math.pow(2, h - 2);
        int left = center - data;
        int right = center + data;

        if(str.charAt(center) == '0'){
            if(str.charAt(left) == '1' || str.charAt(right) == '1'){
                return 0;
            }
        }

        return check(str, left, h-1) & check(str, right, h-1);

    }

    public static String getBinary(long number){
        long data = number;
        StringBuilder sb = new StringBuilder();
        while(data > 1){
            sb.append(data % 2);
            data /= 2;
            if(data == 1){
                sb.append(1);
            }
        }

        while(true){
            if(sb.length() <= Math.pow(2, height) - 1){
                break;
            }
            height++;
        }

        while(sb.length() < Math.pow(2, height) - 1){
            sb.append(0);
        }
        sb.reverse();
        return sb.toString();
    }
}
