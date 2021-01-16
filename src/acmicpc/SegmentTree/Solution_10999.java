package acmicpc.SegmentTree;

import java.io.*;
import java.util.*;
public class Solution_10999 {

    static int N, M, K, size;
    static long[] tree, lazy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        size = 2;
        while (size < N) {
            size *= 2;
        }
        tree = new long[size * 2];
        lazy = new long[size * 2];
        for (int i = 1; i <= N; i++) {
            long num = Long.parseLong(br.readLine());
            tree[size + i - 1] = num;
        }
        for (int i = size - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
        int cmd, a, b, v;
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (cmd == 1) { // update
                v = Integer.parseInt(st.nextToken());
                update(1, 1, size, a, b, v);
            } else {    // sum
                System.out.println(sum(1, 1, size, a, b));
            }
        }

    }

    private static long sum(int node, int start, int end, int i, int j) {
        lazy_update(node, start, end);
        if (j < start || end < i) {
            return 0;
        }
        if (i <= start && end <= j) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, i, j) + sum(node * 2 + 1, mid + 1, end, i, j);
    }

    private static void update(int node, int start, int end, int i, int j, long v) {
        lazy_update(node, start, end);
        if (j < start || end < i) {
            return;
        }
        if (i <= start && end <= j) {
            tree[node] += v * (end - start + 1);
            if (start != end) {
                lazy[node * 2] += v;
                lazy[node * 2 + 1] += v;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, i, j, v);
        update(node * 2 + 1, mid + 1, end, i, j, v);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];

    }

    private static void lazy_update(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += lazy[node] * (end - start + 1);
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }
}
