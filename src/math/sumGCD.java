package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 https://www.acmicpc.net/problem/9613

 문제
 양의 정수 n개가 주어졌을 때, 가능한 모든 쌍의 GCD의 합을 구하는 프로그램을 작성하시오.

 입력
 첫째 줄에 테스트 케이스의 개수 t (1 ≤ t ≤ 100)이 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있다.
 각 테스트 케이스는 수의 개수 n (1 < n ≤ 100)가 주어지고, 다음에는 n개의 수가 주어진다. 입력으로 주어지는 수는 1000000을 넘지 않는다.

 3
 4 10 20 30 40
 3 7 5 12
 3 125 15 25

 출력
 각 테스트 케이스마다 가능한 모든 쌍의 GCD의 합을 출력한다.

 70
 3
 35

 * */
public class sumGCD {

    static int[] arr;
    static long ans;
    static int n, m;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            arr = new int[n];
            ans = 0;
            for (int i = 0; i < n; i++) {
                m = Integer.parseInt(st.nextToken());
                arr[i] = m;
            }

            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    ans += getGCD(arr[i], arr[j]);
                }
            }
            System.out.println(ans);
        }

    }

    static int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }else{
            return getGCD(b, a % b);
        }
    }
}
