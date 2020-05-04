package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.StringTokenizer;

/**
 *
 * 구간 합 구하기
 * https://www.acmicpc.net/problem/2042
 *
 * 문제
 * 어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다.
 * 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다.
 * 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.
 *
 * 입력
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다.
 * M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다.
 * 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데,
 * a가 1인 경우 b번째 수를 c로 바꾸고 a가 2인 경우에는 b번째 수부터 c번째 수까지의 합을 구하여 출력하면 된다.
 *
 * 입력으로 주어지는 모든 수는 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.
 *
 *
 * 5 2 2
 * 1
 * 2
 * 3
 * 4
 * 5
 * 1 3 6
 * 2 2 5
 * 1 5 2
 * 2 3 5
 *
 *
 * 17
 * 12
 *
 */
public class Solution_2042 {

    static long N, M, K;
    static long[] tree;
    static int size;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        size = 2;
        while(size < N){
            size *= 2;
        }

        tree = new long[size * 2];
//        tree = new long[4000001];
        for (int i = 0; i < N; i++) {
            tree[size + i] = Integer.parseInt(br.readLine());
        }

        for (int i = size - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
        int cmd, a, b;
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(cmd == 1){   // update
                change(size + a - 1, b - tree[size + a - 1]);
//                System.out.println("change " + a + " to " + b + " > " +Arrays.toString(tree));
            }else if(cmd == 2){     // sum
                long sum = getSum(size + a - 1, size + b - 1);
                System.out.println(sum);
            }
        }

    }

    private static long getSum(int a, int b) {
        long sum = 0;
        while(a <= b){
            if(a % 2 == 1) sum += tree[a];
            if(b % 2 == 0) sum += tree[b];
            a = (a + 1) / 2;
            b = (b - 1) / 2;
        }
        return sum;
    }

    private static void change(int a, long b) {
        while(a > 0){
            tree[a] += b;
            a /= 2;
        }
    }

}
