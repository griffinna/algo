package acmicpc;

import java.io.*;
import java.util.*;

public class Solution_11505 {
    static int MOD = 1000000007;
    static long[] arr, tree;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        int size = 2;
        while (size < N) {
            size *= 2;
        }
        tree = new long[size * 2];
        init(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                arr[b] = c;
                update(1, 1, N, b, c);
            } else if (a == 2) {
                System.out.println(mul(1, 1, N, b, (int) c));
            }
        }
    }

    public static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = (init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end)) % MOD;
    }

    public static long mul(int node, int start, int end, int i, int j) {
        if (j < start || end < i) {
            return 1;
        }

        if (i <= start && end <= j) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return (mul(node * 2, start, mid, i, j) * mul(node * 2 + 1,mid + 1, end,  i, j)) % MOD;
    }
    public static long update(int node, int start, int end, int idx, long val) {
        if (idx < start || end < idx) {
            return tree[node];
        }
        if (start == end) {
            return tree[node] = val;
        }

        int mid = (start + end) / 2;
        return tree[node] = (update(node * 2, start, mid, idx, val) * update(node * 2 + 1, mid + 1, end, idx, val)) % MOD;
    }

}
