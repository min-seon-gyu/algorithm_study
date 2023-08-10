package codingtest;

import java.util.*;

// 철회해야 할 개인정보의 값을 반환해야 하는 문제
public class Kakao_1_2 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String value : terms) {
            // 약관 종류와 유효 기간 값으로 나누어 종류를 key, 기간을 value로 map에 저장
            String[] split = value.split(" ");
            map.put(split[0], Integer.parseInt(split[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");

            // 약관을 가입한 날짜를 배열로 나눔 ex) "2021.05.02" -> ["2021","05","02"]
            String[] date = split[0].split("\\.");
            // 약관 종류
            String type = split[1];

            // 약관 종류(key)에 해당하는 유효 기간 값(value)을 받아서 가입 날짜와 더해 약관 만료 날짜를 계산함
            int year = map.get(type) / 12 + Integer.parseInt(date[0]);
            int month = map.get(type) % 12 + Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            // month 값이 12가 넘어가면 12를 빼고 year 값 1 증가
            if (month > 12) {
                year++;
                month -= 12;
            }

            // 오늘 날짜와 개인정보 철회 날짜를 비교해서 개인정보 철회 날짜가 지났나면, 리스트에 추가
            if (Integer.parseInt(today.replace(".", "")) >= Integer.parseInt(String.format("%d%02d%02d", year, month, day))) {
                list.add(i + 1);
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
