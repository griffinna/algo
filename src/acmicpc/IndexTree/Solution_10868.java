package acmicpc.IndexTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 최솟값
 *
 * https://www.acmicpc.net/problem/10868
 *
 * 문제
 * N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수를 찾는 것은 어려운 일이 아니다.
 * 하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 이 문제를 해결해 보자.
 *
 * 여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다.
 * 예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최솟값을 찾아야 한다.
 * 각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.
 *
 * 입력
 * 첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다.
 * 다음 M개의 줄에는 a, b의 쌍이 주어진다.
 *
 * 출력
 * M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 출력한다.
 *
 *
 * 10 4
 * 75
 * 30
 * 100
 * 38
 * 50
 * 51
 * 52
 * 20
 * 81
 * 5
 * 1 10
 * 3 5
 * 6 9
 * 8 10
 *
 *
 * 5
 * 38
 * 20
 * 5
 *
 *
 */
public class Solution_10868 {

    static int N, M;
    static long[] tree;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int size = 2;
        while (size < N) {
            size *= 2;
        }
        tree = new long[size * 2];
        Arrays.fill(tree, 1000000001);
        for (int i = 0; i < N; i++) {
            tree[size + i] = Long.parseLong(br.readLine());
        }

        for (int i = size - 1; i > 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            long min = findMin(size + a - 1, size + b - 1);
            System.out.println(min);
        }

    }

    private static long findMin(int a, int b) {
        long min = Long.MAX_VALUE;
        while (a <= b) {
            if(a % 2 == 1) min = Math.min(min, tree[a]);
            if(b % 2 == 0) min = Math.min(min, tree[b]);

            a = (a + 1) / 2;
            b = (b - 1) / 2;
        }

        return min;
    }


}
