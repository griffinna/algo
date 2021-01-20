package acmicpc.DisjointSet;

import java.io.*;
import java.util.*;

public class Solution_11085 {

    static int P, W, C, V;
    static int[] parent, rank;
    static class Road {
        int s, e, w;
        public Road(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        parent = new int[P];
        rank = new int[P];

        for (int i = 0; i < P; i++) {
            parent[i] = i;
        }

        PriorityQueue<Road> pq = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o2.w - o1.w;
            }
        });

        int s, e, w;
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            pq.add(new Road(s, e, w));
        }

        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if (!isConn(now.s, now.e)) {
                union(now.s, now.e);
            }

            if (isConn(C, V)) {
                System.out.println(now.w);
                break;
            }
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
        s = findRoot(s);
        e = findRoot(e);
        return s == e;
    }

    private static int findRoot(int n) {
        if(n == parent[n]) return n;
        return parent[n] = findRoot(parent[n]);
    }

}
