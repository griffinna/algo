package acmicpc.MST;

import java.io.*;
import java.util.*;

public class Solution_4386 {
    static class Edge { int s, e; double dist; }
    static class Star { int idx; double x, y; }

    static class SortEdge implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            if(o1.dist <= o2.dist) return -1;
            return 1;
        }
    }

    static int[] parent, rank;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Star[] list = new Star[N];
        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Star star = new Star();
            star.idx = i + 1;
            star.x = Double.parseDouble(st.nextToken());
            star.y = Double.parseDouble(st.nextToken());
            parent[star.idx] = star.idx;
            list[i] = star;
        }

        PriorityQueue<Edge> edgePq = new PriorityQueue<>(new SortEdge());

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int next = j;
                Edge edge = new Edge();
                edge.s = list[i].idx;
                edge.e = list[next].idx;
                edge.dist = getDistance(list[i].x, list[i].y, list[next].x, list[next].y);
                edgePq.add(edge);
            }
        }

        int cnt = 0;
        double sum = 0;
        while (!edgePq.isEmpty()) {
            Edge now = edgePq.poll();
            if (!isConnect(now.s, now.e)) {
                union(now.s, now.e);
                cnt++;
                sum += now.dist;
            }

            if(cnt == N - 1) break;
        }
        System.out.println(String.format("%.2f", sum));
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

    private static int findRoot(int n) {
        if(n == parent[n]) return n;
        return parent[n] = findRoot(parent[n]);
    }

    private static double getDistance(double x, double y, double x1, double y1) {
        double xd = Math.pow((x1 - x), 2);
        double yd = Math.pow((y1 - y), 2);
        return Math.sqrt(xd + yd);
    }

}
