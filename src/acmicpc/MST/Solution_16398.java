package acmicpc.MST;

import java.io.*;
import java.util.*;

public class Solution_16398 {
    static int N;
    static int[] parent, rank;
    static class Road {
        int s, e, w;
        public Road(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        PriorityQueue<Road> pq = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o1.w - o2.w;
            }
        });
        int n;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                n = Integer.parseInt(st.nextToken());
                if (n != 0 && j >= i) {
                    pq.add(new Road(i, j, n));
                }
            }
        }
        long sum = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if (!isConn(now.s, now.e)) {
                union(now.s, now.e);
                sum += now.w;
                cnt++;
                if(cnt == N - 1) break;
            }
        }
        System.out.println(sum);

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
