package acmicpc.SegmentTree;

import java.io.*;
import java.util.*;

public class Solution_1395 {
    static int N, M;
    static int[] tree, lazy;
    static int size;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        size = 2;
        while (size < N) {
            size *= 2;
        }
        tree = new int[size * 2];
        lazy = new int[size * 2];
        Arrays.fill(lazy, 1);
        int cmd, a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (cmd == 0) { // update
                switching(1, 1, size, a, b);
            } else { // getSum
                System.out.println(count(1, 1, size, a, b));
            }
        }
    }

    private static int count(int node, int s, int e, int i, int j) {
        lazy_switch(node, s, e);
        if(j < s || e < i) return 0;
        if (i <= s && e <= j) {
            return tree[node];
        }
        int mid = (s + e) / 2;
        return count(node * 2, s, mid, i, j) + count(node * 2 + 1, mid + 1, e, i, j);
    }
    private static void switching(int node, int s, int e, int i, int j) {
        lazy_switch(node, s, e);
        if(j < s || e < i) return;
        if(i <= s && e <= j) {
            tree[node] = (e - s + 1) - tree[node];
            if (s != e) {
                lazy[node * 2] *= -1;
                lazy[node * 2 + 1] *= -1;
            }
            return;
        }
        int mid = (s + e) / 2;
        switching(node * 2, s, mid, i, j);
        switching(node * 2 + 1, mid + 1, e, i, j);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void lazy_switch(int node, int s, int e) {
        if (lazy[node] == -1) {
            tree[node] = (e - s + 1) - tree[node];        // 1 ~ 4 구간에 켜진 스위치가 3개였으면, 스위치 후엔 1개가 켜져있다.
            if (s != e) {
                lazy[node * 2] *= -1;
                lazy[node * 2 + 1] *= -1;
            }
        }
        lazy[node] = 1;
    }
}
