package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

 https://www.acmicpc.net/problem/10430

 문제
 (A + B) % C, (A % C + B % C) % C,
 (A X B) % C, (A % C X B % C) % C,
 위의 네가지 값 출력

 in : 5 8 4
 out :
 1
 1
 0
 0

 첫째 줄에 (A+B)%C, 둘째 줄에 (A%C + B%C)%C, 셋째 줄에 (A×B)%C, 넷째 줄에 (A%C × B%C)%C를 출력한다.
* */
public class Left {

    static int A, B, C;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println((A + B) % C);
        System.out.println((A % C + B % C) % C);
        System.out.println((A * B) % C);
        System.out.println((A % C * B % C) % C);

    }

}
