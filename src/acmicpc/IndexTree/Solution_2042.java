package acmicpc.IndexTree;

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
        /**
           2^i 개의 노드를 가진 트리로 만든다.
           1 ~ 8까지 8개의 구간 합이 필요하면
                    1
               2         3
            4    5     6     7
           8 9 10 11 12 13 14 15

           위와 같이 총 15개(idx 는 0부터니까 16개)가 필요하고,
           리프노드층의 시작은 2^i 로 시작한다.
        */
        size = 2;
        while(size < N){
            size *= 2;
        }

        tree = new long[size * 2];
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
                /**
                   * bottom up *
                    실제 index 의 숫자가 저장된 리프노드부터 1 번까지 update 하기 위한 쿼리
                    a : (leaf node 층의 시작인) size + a - 1
                    b : (leaf node 층의 시작인) size + b - 1
                 */
                change(size + a - 1, b - tree[size + a - 1]);
            }else if(cmd == 2){     // sum
                long sum = getSum(size + a - 1, size + b - 1);
                System.out.println(sum);
            }
        }

    }

    private static long getSum(int a, int b) {
        long sum = 0;
        /**
            a 는 왼쪽부터 중앙으로 b는 오른쪽에서부터 중앙으로 이동한다.
            왼쪽자식노드는 짝수번 node 인데, 왼쪽의 a 가 홀수라면 해당 node 값은 즉시 더해줘야한다.
            오른쪽자식노드는 홀수번 node 인데, 오른쪽의 b 가 짝수라면 해당 node 값은 즉시 더해줘야한다.
         * */
        while(a <= b){
            if(a % 2 == 1) sum += tree[a];
            if(b % 2 == 0) sum += tree[b];
            a = (a + 1) / 2;
            b = (b - 1) / 2;
        }
        return sum;
    }

    private static void change(int a, long b) {
        /**
            leaf node 부터 root node 로 올라가면서 (a /= 2)
            지나가는 모든 노드에 값을 증가시킨다.
        * */
        while(a > 0){
            tree[a] += b;
            a /= 2;
        }
    }

}
