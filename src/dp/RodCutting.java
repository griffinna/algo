package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 문제
 * 길이가 N 인 막대기가 있다. 막대기를 길이가 자연수가 되도록 여러 조각으로 자를 수 있다.
 * 각 길이에 대해 값어치가 있을 때, 값어치 합이 최대가 되도록 막대기를 자르자.
 * 예를 들어, 길이가 4인 막대기가 있고 각 길이 별 값어치가 아래와 같다고 하자.
 * |  length  | 1 | 2 | 3 | 4 |
 * |----------|---|---|---|---|
 * |   cost   | 1 | 5 | 8 | 9 |
 * 이 때, 길이 2인 막대기가 두 개가 되도록 전체 막대기를 자를 경우 전체 값어치가 10이 되어 최대가 된다.
 *
 * 입력
 * 첫 줄에 막대기의 길이 N이 주어진다. (1 ≤ N ≤ 3,000)
 * 둘째 줄에 N개의 자연수가 공백으로 구분되어 주어지는데, i번째로 주어지는 수는 길이가 i인 막대기의 값어치를 의미한다.
 * 이 때 주어지는 수는 1,000를 넘지 않는다.
 *
 * 출력
 * 첫 줄에 값어치 합이 최대가 되도록 막대기를 자를 때, 값어치 합을 출력한다.
 *
 * 예제 입력
 * 4
 * 1 5 8 9
 *
 * 예제 출력
 * 10
 * */
public class RodCutting {

    static int N;
    static int[] cost;
    static int MAX = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        int[] totalCost = new int[N + 1];
        cost[0] = 0;
        totalCost[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < cost.length; i++) {
            int maxNum = 0;
            for (int j = 1; j <= i; j++) {
                int num = cost[j] + totalCost[i - j];
                maxNum = (maxNum < num) ? num : maxNum;
            }
            totalCost[i] = maxNum;
            if(MAX < totalCost[i]) MAX = totalCost[i];
        }
        System.out.println(MAX);
    }

}
