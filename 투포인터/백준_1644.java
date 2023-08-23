import java.util.*;
import java.io.*;

public class 백준_1644 {

    static int n;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        br.close();

        for (int i = 0; i <= n; i++) {
            if (isPrime(i)) list.add(i);
        }
        System.out.println(search());
    }

    public static int search() {
        int start = 0;
        int end = 0;
        int sum = 0;
        int result = 0;
        while (true) {
            if (sum >= n) {
                sum -= list.get(start++);
            } else if (end == list.size()) {
                break;
            } else {
                sum += list.get(end++);
            }

            if (sum == n) result++;
        }
        return result;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}