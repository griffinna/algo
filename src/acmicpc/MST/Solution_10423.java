package acmicpc.MST;

import java.io.*;
import java.util.*;

public class Solution_10423 {
    static int N, M, K;
    static int[] parent, rank;
    static int ANS;
    static class Edge {
        int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class SortEdge implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.w - o2.w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int prev = 0;
        int next;
        for (int i = 0; i < K; i++) {
            next = Integer.parseInt(st.nextToken());
            union(prev, next);
            prev = next;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(new SortEdge());
        int a, b, w;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, w));
        }

        ANS = 0;
        int edgeCnt = K - 1;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!isConn(now.u, now.v)) {
                union(now.u, now.v);
                ANS += now.w;
                edgeCnt++;
                if(edgeCnt == N - 1) break;
            }
        }
        System.out.println(ANS);
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

    private static boolean isConn(int u, int v) {
        return findRoot(u) == findRoot(v);
    }

    private static int findRoot(int n) {
        if(n == parent[n]) return n;
        return parent[n] = findRoot(parent[n]);
    }
}
