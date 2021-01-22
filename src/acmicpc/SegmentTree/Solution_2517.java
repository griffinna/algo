package acmicpc.SegmentTree;

import java.io.*;
import java.util.*;

public class Solution_2517 {
    static int[] tree;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] list  = new int[N][2];
        tree = new int[N * 4];
        int speed;
        for (int i = 0; i < N; i++) {
            speed = Integer.parseInt(br.readLine());
            list[i][0] = i + 1;
            list[i][1] = speed;
        }
        Arrays.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int[] ans = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int idx = list[i][0];
            int cnt = countPlayer(1, 1, N, 1,idx - 1);
            ans[idx] = cnt + 1;
            update(1, 1, N, idx);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i + 1]).append("\n");
        }
        System.out.println(sb.toString());

    }

    private static void update(int node, int s, int e, int idx) {
        if(idx < s || e < idx) return;
        tree[node] += 1;
        if (s != e) {
            int mid = (s + e) / 2;
            update(node * 2, s, mid, idx);
            update(node * 2 + 1, mid + 1, e, idx);
        }
    }

    private static int countPlayer(int node, int s, int e, int i, int j) {
        if (j < s || e < i) {
            return 0;
        }
        if (i <= s && e <= j) {
            return tree[node];
        }
        int mid = (s + e) / 2;
        return countPlayer(node * 2, s, mid, i, j) + countPlayer(node * 2 + 1, mid + 1, e, i, j);
    }

}
