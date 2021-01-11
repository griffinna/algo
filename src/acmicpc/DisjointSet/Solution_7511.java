package acmicpc.DisjointSet;

import java.io.*;
import java.util.*;

public class Solution_7511 {
    static int N, K, M;
    static int[] parent, rank;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());    // 친구수
            K = Integer.parseInt(br.readLine());    // 관계수
            parent = new int[N + 1];
            rank = new int[N + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            int a, b;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            sb.append("Scenario " + test_case + ":\n");
            M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (isFriend(a, b)) {
                    sb.append("1\n");
                }else{
                    sb.append("0\n");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void union(int n, int m) {
        n = findRoot(n);
        m = findRoot(m);
        if (rank[n] < rank[m]) {
            parent[n] = parent[m];
        } else {
            parent[m] = parent[n];
            if (rank[n] == rank[m]) {
                rank[n]++;
            }
        }
    }

    private static int findRoot(int n) {
        if(n == parent[n]) return n;
        return parent[n] = findRoot(parent[n]);
    }

    private static boolean isFriend(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

}
