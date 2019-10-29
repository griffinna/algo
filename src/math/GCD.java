package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

 https://www.acmicpc.net/problem/2609

 문제
 두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.

 입력
 첫째 줄에는 두 개의 자연수가 주어진다. 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.

 24 18

 출력
 첫째 줄에는 입력으로 주어진 두 수의 최대공약수를,둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.

 6
 72

 유클리드 호제법

 GCD(A, B) = A, B의 최대공약수
 r = A % B 일 때, GCD(A, B) = GCD(B, r)이다.
 단, r == 0이면 GCD(B, r) = B이다.
 딱 r = A % B = 0으로, 딱 나누어 떨어지므로 최대공약수는 B

 최대공약수(GCD)

 즉, A와 B로 나눈 나머지를 구하여
 0인지 확인 한 후 0이 아니라면 0일때 까지 반복하고
 0인 경우 나눈 값이 최대공약수이므로 리턴하면 된다.
 최소공배수(LCM)

 A,B의 최소공배수
 = 최대공약수 * (A/최대공약수) * (B/최대공약수)
 = A * (B/최대공약수)


 * */
public class GCD {

    static int A, B, G, L;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        G = getGDC(A, B);
        L = (A * B) / G;

        System.out.println(G);
        System.out.println(L);

    }

    private static int getGDC(int a, int b) {
        if (b == 0) {
            return a;
        }else{
            return getGDC(b, a % b);
        }
    }
}
