package acmicpc.MST;

import java.io.*;
import java.util.*;

public class Solution_2887 {

    static class Planet { int idx, x, y, z; }
    static class Edge { int s, e, cost; }

    static class costSort implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.cost - o2.cost;
        }
    }

    static class xSort implements Comparator<Planet> {
        @Override
        public int compare(Planet o1, Planet o2) {
            return o1.x - o2.x;
        }
    }

    static class ySort implements Comparator<Planet> {
        @Override
        public int compare(Planet o1, Planet o2) {
            return o1.y - o2.y;
        }
    }

    static class zSort implements Comparator<Planet> {
        @Override
        public int compare(Planet o1, Planet o2) {
            return o1.z - o2.z;
        }
    }

    static int[] parent, rank;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] pos = new int[N + 1][3];
        parent = new int[N + 1];
        rank = new int[N + 1];
        Planet[] list = new Planet[N];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            Planet p = new Planet();
            p.idx = i;
            p.x = Integer.parseInt(st.nextToken());
            p.y = Integer.parseInt(st.nextToken());
            p.z = Integer.parseInt(st.nextToken());
            parent[i] = i;
            list[i - 1] = p;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(new costSort());

        Arrays.sort(list, new xSort());
        for (int i = 0; i < N - 1; i++) {
            Edge edge = new Edge();
            edge.s = list[i].idx;
            edge.e = list[i + 1].idx;
            edge.cost = Math.abs(list[i].x - list[i + 1].x);
            pq.add(edge);
        }

        Arrays.sort(list, new ySort());
        for (int i = 0; i < N - 1; i++) {
            Edge edge = new Edge();
            edge.s = list[i].idx;
            edge.e = list[i + 1].idx;
            edge.cost = Math.abs(list[i].y - list[i + 1].y);
            pq.add(edge);
        }

        Arrays.sort(list, new zSort());
        for (int i = 0; i < N - 1; i++) {
            Edge edge = new Edge();
            edge.s = list[i].idx;
            edge.e = list[i + 1].idx;
            edge.cost = Math.abs(list[i].z - list[i + 1].z);
            pq.add(edge);
        }

        int totalCost = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!isConnect(now.s, now.e)) {
                totalCost += now.cost;
                cnt++;
                union(now.s, now.e);
            }
            if(cnt == N - 1){
                break;
            }
        }
        System.out.println(totalCost);
    }

    private static boolean isConnect(int s, int e) {
        return findRoot(s) == findRoot(e);
    }

    private static int findRoot(int s) {
        if(s == parent[s]) return s;
        return parent[s] = findRoot(parent[s]);
    }

    private static void union(int n, int m) {
        n = findRoot(n);
        m = findRoot(m);
        if (rank[n] < rank[m]) {
            parent[n] = parent[m];
        }else{
            parent[m] = parent[n];
            if (rank[n] == rank[m]) {
                rank[n]++;
            }
        }
    }
}
