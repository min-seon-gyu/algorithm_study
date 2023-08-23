package 카카오2022;

import java.util.*;

public class Kakao_2 {

    public static void main(String[] args) {
        //case 1
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};

        //case 2
        int[] queue3 = {1, 2, 1, 2};
        int[] queue4 = {1, 10, 1, 2};

        //case 3
        int[] queue5 = {1, 1};
        int[] queue6 = {1, 5};

        //case 1
        //System.out.println(run(queue1, queue2));
        //case 2
        //System.out.println(run(queue3, queue4));
        //case 3
        //System.out.println(run(queue5, queue6));

        int[] queue7 = {2, 2};
        int[] queue8 = {2};
        System.out.println(run(queue7, queue8));
    }

    public static int run(int[] queue1, int[] queue2){
        long q1Sum = 0;
        long q2Sum = 0;
        int count = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long max = 0;

        for(int i : queue1){
            q1.offer(i);
            q1Sum += i;
            max = Math.max(max, i);
        }

        for(int i : queue2){
            q2.offer(i);
            q2Sum += i;
            max = Math.max(max, i);
        }

        if((q1Sum + q2Sum) % 2 != 0 || max * 2 > q1Sum + q2Sum){
            return -1;
        }

        while(q1Sum != q2Sum){

            if(queue1.length * 3 < count){
                count = -1;
                break;
            }

            int q1Number = q1.peek();
            int q2Number = q2.peek();

            if(q1Sum > q2Sum){

                q2.offer(q1.poll());
                q1Sum -= q1Number;
                q2Sum += q1Number;

            }
            else if(q1Sum < q2Sum){

                q1.offer(q2.poll());
                q1Sum += q2Number;
                q2Sum -= q2Number;

            }
            count++;
        }
        return count;
    }
}
