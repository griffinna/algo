package acmicpc.Dijkstra;

import java.io.*;
import java.util.*;

public class Solution_2211 {

    static class Computer {
        int idx, t;
        public Computer(int idx, int t) {
            this.idx = idx;
            this.t = t;
        }
    }

    static class SortComputer implements Comparator<Computer> {
        @Override
        public int compare(Computer o1, Computer o2) {
            return o1.t - o2.t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Computer>[] adj = new ArrayList[N + 1];
        int[] dist = new int[N + 1];
        int[] parent = new int[N + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(adj[a] == null) adj[a] = new ArrayList<>();
            if(adj[b] == null) adj[b] = new ArrayList<>();
            adj[a].add(new Computer(b, c));
            adj[b].add(new Computer(a, c));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Computer> pq = new PriorityQueue<>(new SortComputer());
        pq.add(new Computer(1, dist[1]));
        while (!pq.isEmpty()) {
            Computer now = pq.poll();
            if(adj[now.idx] != null) {
                for (Computer next : adj[now.idx]) {
                    if (dist[next.idx] > dist[now.idx] + next.t) {
                        dist[next.idx] = dist[now.idx] + next.t;
                        pq.add(new Computer(next.idx, dist[next.idx]));
                        parent[next.idx] = now.idx;
                    }
                }
            }
        }
        System.out.println(N - 1);
        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i] + " " + i);
        }
    }
}
