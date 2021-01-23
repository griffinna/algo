package acmicpc.DisjointSet;

import java.io.*;
import java.util.*;

public class Solution_1976 {
    static int N, M;
    static int[] rank, parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        rank = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n;
            for (int j = 1; j <= N; j++) {
                n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    if (!isConn(i, j)) {
                        union(i, j);
                    }
                }
            }
        }

        boolean isPossible = true;
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        for (int i = 1; i < M; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (!isConn(prev, next)) {
                isPossible = false;
                break;
            } else {
                prev = next;
            }
        }
        if (isPossible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean isConn(int a, int b) {
        return getRoot(a) == getRoot(b);
    }

    static void union(int n, int m) {
        n = getRoot(n);
        m = getRoot(m);

        if (rank[n] < rank[m]) {
            parent[n] = parent[m];
        } else {
            parent[m] = parent[n];
            if (rank[n] == rank[m]) {
                rank[n]++;
            }
        }
    }

    private static int getRoot(int m) {
        if(m == parent[m]) return m;
        return parent[m] = getRoot(parent[m]);
    }
}
