import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 백준_9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String keyword = br.readLine();
        Stack<Character> s = new Stack();

        /* 메모리 초과
        while(str.indexOf(keyword) >= 0){
            str = str.replace(keyword, "");
        }*/

        for(int i = 0 ; i < str.length(); i++){
            char c = str.charAt(i);
            s.push(c);

            if(s.size() >= keyword.length()){

                boolean result = true;
                for(int j = 0; j < keyword.length(); j++){
                    if(s.get(s.size() - keyword.length() + j) != keyword.charAt(j)){
                        result = false;
                        break;
                    }
                }

                if(result){
                    for(int j = 0; j < keyword.length(); j++){
                        s.pop();
                    }
                }
            }
        }

        if(s.size() == 0){
            System.out.println("FRULA");
        }else{
            StringBuilder sb = new StringBuilder();
            for(Character c : s){
                sb.append(c);
            }
            System.out.println(sb);
        }
    }
}
