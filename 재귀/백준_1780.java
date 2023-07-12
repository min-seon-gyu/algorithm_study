import java.io.*;
import java.util.*;

public class Baekjoon_1780 {

    static int[] count = new int[3];

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            int[][] paper = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            recursive(paper);
            System.out.println(count[0]);
            System.out.println(count[1]);
            System.out.println(count[2]);
        } catch (IOException e) {
        }
    }

    public static void recursive(int[][] board) {
        if (isAllNumberSame(board)) {
            int value = board[0][0];
            count[value + 1] = count[value + 1] + 1;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int[][] split = splitBoard(board, board.length / 9, i * 3, j * 3);
                    recursive(split);
                }
            }
        }
    }

    public static boolean isAllNumberSame(int[][] board) {
        int value = board[0][0];
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                if (board[i][j] != value)
                    return false;
            }
        }
        return true;
    }

    public static int[][] splitBoard(int[][] board, int size, int startX, int startY) {
        int[][] split = new int[size / 3][size / 3];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                split[i][j] = board[i + startX][i + startY];
            }
        }
        return split;
    }
}