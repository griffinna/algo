package acmicpc.MST;

import java.io.*;
import java.util.*;

public class Solution_14621 {
    static int[] parent, rank;
    static int[] type;
    static class Edge { int s, e, cost; }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        rank = new int[N + 1];
        type = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int t = (st.nextToken().equals("W")) ? 1 : 2;
            type[i] = t;
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost - o2.cost;
            }
        });

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Edge edge = new Edge();
            edge.s = Integer.parseInt(st.nextToken());
            edge.e = Integer.parseInt(st.nextToken());
            edge.cost = Integer.parseInt(st.nextToken());
            if(type[edge.s] != type[edge.e]) pq.add(edge);
        }
        int cnt = 0;
        int sum = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!isConnect(now.s, now.e)) {
                cnt++;
                sum += now.cost;
                union(now.s, now.e);
            }
            if(cnt == N - 1) break;
        }

        if (cnt < N - 1) {
            System.out.println(-1);
        }else{
            System.out.println(sum);
        }
    }

    private static boolean isConnect(int s, int e) {
        return findRoot(s) == findRoot(e);
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
    private static int findRoot(int a) {
        if(a == parent[a]) return a;
        return parent[a] = findRoot(parent[a]);
    }
}
