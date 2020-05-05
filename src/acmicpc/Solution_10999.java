package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제
 * 어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다.
 * 만약에 1,2,3,4,5 라는 수가 있고, 3번째부터 4번째 수에 6을 더하면 1, 2, 9, 10, 5가 되고,
 * 여기서 2번째부터 5번째까지 합을 구하라고 한다면 26을 출력하면 되는 것이다.
 * 그리고 그 상태에서 1번째부터 3번째 수에 2를 빼고 2번째부터 5번째까지 합을 구하라고 한다면 22가 될 것이다.
 *
 * 입력
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다.
 * M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다.
 * 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c 또는 a, b, c, d가 주어지는데,
 * a가 1인 경우 b번째 수부터 c번째 수에 d를 더하고, a가 2인 경우에는 b부터 c까지의 합을 구하여 출력하면 된다.
 *
 * 입력으로 주어지는 모든 수는 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.
 *
 * 5 2 2
 * 1
 * 2
 * 3
 * 4
 * 5
 * 1 3 4 6
 * 2 2 5
 * 1 1 3 -2
 * 2 2 5
 *
 *
 * 26
 * 22
 *
 */
public class Solution_10999 {

    static int N, M, K;
    static long[] tree, lazy;
    static long[] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int size = 2;
        while(N > size){
            size *= 2;
        }
        tree = new long[size * 2 + 1];
        lazy = new long[size * 2 + 1];
        arr = new long[size + 1];

        for (int i = 0; i < N; i++) {
//            tree[size + i] = Integer.parseInt(br.readLine());
            arr[i + 1] = Long.parseLong(br.readLine());
        }
        makeTree(1, size, 1);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(tree));

        int cmd, a, b, c;
        for (int i = 0; i < M + K; i++) {

            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(cmd == 1){       // update
                c = Integer.parseInt(st.nextToken());
                updateNode(1, 1, size, a, b, c);
//                System.out.println(Arrays.toString(tree));
            } else if (cmd == 2) {      // get sum
//                long sum = getTotalSum(1, 1, size * 2 + 1, a, b);
                long sum = getTotalSum(1, 1, size, a, b);
                System.out.println(sum);
            }

        }

    }

    private static long getTotalSum(int node, int left, int right, int start, int end) {
        updateLazy(node, left, right);
        if(right < start || end < left){        // 구간 밖
            return 0;
        }
        if(start <= left && right <= end){      // 포함
            return tree[node];
        }
        int mid = (left + right) / 2;
        return getTotalSum(node * 2, left, mid, start, end)
                + getTotalSum(node * 2 + 1, mid + 1, right, start, end);

    }

    static void updateLazy(int node, int left, int right) {
        if(lazy[node] != 0){
            tree[node] += (right - left + 1) * lazy[node];
            if (left != right) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    private static void updateNode(int node, int left, int right, int start, int end, long value) {
        updateLazy(node, left, right);
        if (start > right || end < left) {
            return;
        }

        if(start <= left && right <= end){
            tree[node] += (right - left + 1) * value;
            if (left != right) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }
            return;
        }

        int mid = (left + right) / 2;
        updateNode(node * 2, left, mid, start, end, value);
        updateNode(node * 2 + 1, mid + 1, right, start, end, value);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long makeTree(int left, int right, int node) {
        if(left == right){
            return tree[node] = arr[left];
        }else{
            int mid = (left + right) / 2;

            return tree[node] = makeTree(left, mid, node * 2)
                                + makeTree(mid + 1, right, node * 2 + 1);
        }
    }
}
