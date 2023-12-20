package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


/*

문제 해설
규칙 1 : 큰 숫자를 왼쪽 작은 숫자를 오른쪽에 사용한다.
규칙 2 : V, L, D 는 1번 나머지는 3번까지 가능 -> 만들 수 있는 숫자는 1,2,3,5,6,7,8
규칙 3 : 작은 숫자를 왼쪽에 사용하는 경우는 (큰 숫자 - 작은 숫자)를 사용할 때(한 번씩 사용 가능! 언제? 4,9 만들때!)
규칙 4 : 최소한 길이로 만들자!

결론!

1,2,3 일 때는 그대로 쓰기
4 일 때는 큰 값에서 작은 값 빼서 쓰기
5,6,7,8 일 때는 그대로 쓰기
9 일 때는 큰 값에서 작은 값 빼서 쓰기

 */
public class 백준_2608 {
    static HashMap<Character, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);

        String a = br.readLine();
        String b = br.readLine();

        System.out.println(getNumber(a) + getNumber(b));
        System.out.println(getRomeNumber(getNumber(a) + getNumber(b)));
    }

    private static String getRomeNumber(int number) {
        StringBuilder sb = new StringBuilder();

        int first = number / 1 % 10;
        int second = number / 10 % 10;
        int third = number / 100 % 10;
        int fourth = number / 1000 % 10;

        while(fourth > 0){
            sb.append("M");
            fourth--;
        }

        get(sb, third, "C", "D", "M");
        get(sb, second, "X", "L", "C");
        get(sb, first, "I", "V", "X");

        return sb.toString();
    }

    private static void get(StringBuilder sb, int num, String s1, String s2, String s3) {
        while(num > 0){
            if(num <= 3){
                sb.append(s1);
                num--;
            }
            else if(num == 4){
                sb.append(s1).append(s2);
                num -= 4;
            }
            else if(num <= 8){
                sb.append(s2);
                num -= 5;
            }else{
                sb.append(s1).append(s3);
                num -= 9;
            }
        }
    }

    private static int getNumber(String str) {

        int answer = 0;

        for(int i = 0 ; i < str.length(); i++){
            if(i == str.length() - 1){
                answer += hm.get(str.charAt(i));
                break;
            }

            int first = hm.get(str.charAt(i));
            int second = hm.get(str.charAt(i + 1));

            if(first < second){
                answer += second-first;
                i++;
            }else{
                answer += first;
            }
        }

        return answer;

    }

}
