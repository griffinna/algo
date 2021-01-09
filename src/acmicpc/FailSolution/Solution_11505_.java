package acmicpc.FailSolution;

import java.io.*;
import java.util.*;

public class Solution_11505_ {
    static int MOD = 1000000007;
    static long[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // count n
        int M = Integer.parseInt(st.nextToken());   // update
        int K = Integer.parseInt(st.nextToken());   // getMultiple

        int size = 2;
        while(size < N) size *= 2;
        tree = new long[size * 2 + 1];

        int n;
        Arrays.fill(tree, 1);
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(br.readLine());
            tree[size + i] = n;
        }

        for (int i = size - 1; i > 0; i--) {
            tree[i] = tree[i * 2] * tree[i * 2 + 1];
        }
//        System.out.println(Arrays.toString(tree));
        int cmd, a, b;
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                update(size + a - 1, b);
            } else {
                System.out.println(getMultiple(size + a - 1, size + b - 1));
            }
//            System.out.println(Arrays.toString(tree));
        }

    }

    private static long getMultiple(int a, int b) {
        long ans = 1;
        while (a <= b) {
            if (a % 2 == 1) {
                ans = (ans * tree[a]) % MOD;
            }

            if(b % 2 == 0) {
                ans = (ans * tree[b]) % MOD;
            }

            a = (a + 1) / 2;
            b = (b - 1) / 2;
        }
        return ans;
    }

    private static void update(int a, int b) {
        tree[a] = b;
        a /= 2;
        while (a >= 1) {
            tree[a] = (tree[a * 2] * tree[a * 2 + 1]) % MOD;
            a /= 2;
        }
    }

}
