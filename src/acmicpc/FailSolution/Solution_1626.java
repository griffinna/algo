package acmicpc.FailSolution;

import java.io.*;
import java.util.*;

public class Solution_1626 {
    static int V, E;
    static int[] parent, rank;
    static int ANS;
    static class Edge {
        int s, e, cost;
        public Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }

    static class SortEdge implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.cost - o2.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        rank = new int[V + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(new SortEdge());
        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }
        int edgeCnt = 0;
        boolean findMST = false;
        boolean findScdMST= false;
        ANS = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!isConn(now.s, now.e)) {
                union(now.s, now.e);
                System.out.print(now.cost + " + ");
                ANS += now.cost;
                edgeCnt++;
            }
            if (!findMST && edgeCnt == V - 1) {
                findMST = true;
                edgeCnt--;
                ANS -= now.cost;
                System.out.print(" - " + now.cost);
                // undo last
                parent[now.s] = now.s;
                parent[now.e] = now.e;
            }

            if (findMST && edgeCnt == V - 1) {
                findScdMST = true;
                break;
            }
        }

        if (!findMST || !findScdMST) {
            System.out.println(-1);
        } else {
            System.out.println(ANS);
        }
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

    private static boolean isConn(int s, int e) {
        return findRoot(s) == findRoot(e);
    }

    private static int findRoot(int a) {
        if(a == parent[a]) return a;
        return parent[a] = findRoot(parent[a]);
    }
}
