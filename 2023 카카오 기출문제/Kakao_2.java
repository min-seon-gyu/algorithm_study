import java.util.*;

public class Kakao_2 {

    public static class House{
        int count;
        int loc;
        public House(int count, int loc){
            this.count = count;
            this.loc = loc;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
    }


    public static long solution(int cap, int n, int[] deliveries, int[] pickups){

        long answer = 0;

        Deque<House> deliveriesDeque = new LinkedList<>();
        Deque<House> pickupsDeque = new LinkedList<>();


        for(int i = n - 1; i >= 0 ; i--){
            if(deliveries[i] > 0){
                int count = deliveries[i];
                int loc = i + 1;
                deliveriesDeque.offer(new House(count, loc));
            }
            if(pickups[i] > 0){
                int count = pickups[i];
                int loc = i + 1;
                pickupsDeque.offer(new House(count, loc));
            }
        }

        while(!deliveriesDeque.isEmpty() || !pickupsDeque.isEmpty()){


            if(!deliveriesDeque.isEmpty() && !pickupsDeque.isEmpty()){
                int deliveriesLoc = deliveriesDeque.peek().loc;
                int pickupsLoc = pickupsDeque.peek().loc;
                answer += deliveriesLoc > pickupsLoc ? deliveriesLoc * 2 : pickupsLoc * 2;
            }
            else if(!deliveriesDeque.isEmpty()){
                answer += deliveriesDeque.peek().loc * 2;
            }
            else if(!pickupsDeque.isEmpty()){
                answer += pickupsDeque.peek().loc * 2;
            }



            int capValue = 0;
            while(!deliveriesDeque.isEmpty()){
                House h = deliveriesDeque.peek();
                if(capValue + h.count<= cap){
                    capValue += h.count;
                    deliveriesDeque.poll();
                }else{
                    deliveriesDeque.poll();
                    deliveriesDeque.offerFirst(new House(h.count - (cap - capValue), h.loc));
                    break;
                }
            }

            capValue = 0;
            while(!pickupsDeque.isEmpty()){
                House h = pickupsDeque.peek();
                if(capValue + h.count <= cap){
                    capValue += h.count;
                    pickupsDeque.poll();
                }else{
                    pickupsDeque.poll();
                    pickupsDeque.offerFirst(new House(h.count - (cap - capValue), h.loc));
                    break;
                }
            }


        }
        return answer;
    }
}