package acmicpc.LCA;

import java.io.*;
import java.util.*;

public class Solution_1761 {
    static int[][] parent;
    static int[] d, fromRoot;
    static boolean[] visit;
    static ArrayList<Node>[] adj;
    static class Node {
        int idx, dist;
        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
    static int N, MD;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int a, b, c;
        MD = 0;
        while(Math.pow(2, MD) < N) {
            MD++;
        }
        parent = new int[N + 1][MD];
        d = new int[N + 1];
        fromRoot = new int[N + 1];
        visit = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            parent[b][0] = a;

            if(adj[a] == null) adj[a] = new ArrayList<>();
            if(adj[b] == null) adj[b] = new ArrayList<>();
            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        bfs(1, 0);
        setParent();
        int M = Integer.parseInt(br.readLine());
        int lca;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lca = LCA(a, b);
            System.out.println(fromRoot[a] + fromRoot[b] - (fromRoot[lca] * 2));
        }
    }

    private static int LCA(int x, int y) {
        if (d[x] > d[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for (int i = MD - 1; i >= 0; i--) {
            if (d[y] - d[x] >= (1 << i)) {
                y = parent[y][i];
            }
        }

        if(x == y) return x;

        for (int i = MD - 1; i >= 0; i--) {
            if (parent[x][i] != parent[y][i]) {
                x = parent[x][i];
                y = parent[y][i];
            }
        }
        return parent[y][0];
    }

    private static void setParent() {
        for (int j = 1; j < MD; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }
    }

    private static void bfs(int x, int depth) {
        visit[x] = true;
        d[x] = depth;
        if (adj[x] != null) {
            for (Node next: adj[x]) {
                if(visit[next.idx]) continue;
                fromRoot[next.idx] = fromRoot[x] + next.dist;
                parent[next.idx][0] = x;
                bfs(next.idx, depth + 1);
            }
        }
    }
}
