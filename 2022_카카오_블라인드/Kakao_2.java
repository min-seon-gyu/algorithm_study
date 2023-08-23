package codingtest;

// 신고 결과 받기: https://school.programmers.co.kr/learn/courses/30/lessons/92334
public class Programmers_92334 {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        // 신고 결과, 어떤 유저가 몇 번 신고 당했는지를 나타냄
        Map<String, Integer> reportResult = new HashMap<>();
        // 누가 누구를 신고했는지, 중복 제거를 위해 Set으로 선언
        Map<String, Set<String>> whoReport = new HashMap<>();

        // 신고 결과 계산
        for (String value : report) {
            String[] split = value.split(" ");
            String user = split[0];
            String reportedUser = split[1];

            // 현재 유저가 신고한 유저 목록 조회
            Set<String> reportedListByCurrentUser = whoReport.getOrDefault(user, new HashSet<>());
            if (!reportedListByCurrentUser.contains(reportedUser)) {
                // 이미 신고한 유저라면 넘어가고, 처음 신고했다면 count 값 추가
                reportResult.put(reportedUser, reportResult.getOrDefault(reportedUser, 0) + 1);
                reportedListByCurrentUser.add(reportedUser);
                whoReport.put(user, reportedListByCurrentUser);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            Set<String> reported = whoReport.get(id_list[i]);
            if (reported == null) continue;

            int count = 0;
            for (String key : reported) {
                if (reportResult.getOrDefault(key, 0) >= k) {
                    count++;
                }
            }
            answer[i] = count;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] ids = new String[] {"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        System.out.println(Arrays.toString(solution(ids, report, 2)));
    }
}