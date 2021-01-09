package acmicpc.LIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 구간 합 구하기 4
 *
 * https://www.acmicpc.net/problem/11659
 *
 * 문제
 * 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수의 개수 N (1 ≤ N ≤ 100,000), 합을 구해야 하는 횟수 M (1 ≤ M ≤ 100,000)이 주어진다.
 * 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다. 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
 *
 * 출력
 * 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
 *
 * 5 3
 * 5 4 3 2 1
 * 1 3
 * 2 4
 * 5 5
 *
 * 12
 * 9
 * 1
 *
 */
public class Solution_11659 {

    static int N;   // 주어진 수의 개수    (1 <= N <= 100,000)
    static int M;   // 합을 구해야 하는 횟수 (1 <= M <= 100,000)
    static int[] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            if(i == 1){
                arr[i] = Integer.parseInt(st.nextToken());
            }else{
                arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
            }
        }

        int a, b;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            System.out.println(arr[b] - arr[a - 1]);
        }
    }
}
