import java.io.*;
import java.util.*;

public class Baekjoon_1759 {

    static int l, c;
    static char[] result, values;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        result = new char[l];
        values = new char[c];
        checked = new boolean[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) { // 데이터 초기화
            values[i] = st.nextToken().charAt(0);
        }
        br.close();
        Arrays.sort(values);
        check(0);
        System.out.print(sb.toString());
    }

    public static void check(int index) {
        if (index == l) {
            int count = 0;
            for (char value : result) { // 자음 개수 확인
                if (!(value == 'a' || value == 'e' || value == 'i' || value == 'o' || value =='u'))
                    count++;
            }
            if (count >= 2 && count < l) { // 자음이 2개 이상이고 모음이 1개 이상일 때
                for (char value : result) {
                    sb.append(value);
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = 0; i < c; i++) {
            if (!checked[i]) {
                // 오름차순 정렬, 이전 값이 현재 값보다 크다면 continue
                if (index > 0 && result[index - 1] > values[i])
                    continue;
                checked[i] = true;
                result[index] = values[i];
                check(index + 1);
                checked[i] = false;
            }
        }
    }
}
