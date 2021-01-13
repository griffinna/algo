package acmicpc.LCA;

import java.io.*;
import java.util.*;

public class Solution_11437 {
    static boolean[] visit;
    static int[] d;
    static int[][] parent;
    static ArrayList<Integer>[] adj;
    static int N, MD;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MD = 0;
        while (Math.pow(2, MD) < N) {
            MD++;
        }

        visit = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        d = new int[N + 1];
        parent = new int[N + 1][MD];

        int a, b;
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(adj[a] == null) adj[a] = new ArrayList<>();
            if(adj[b] == null) adj[b] = new ArrayList<>();
            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1, 0);  // 1번 정점이 depth 0
        setParent();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(LCA(a, b));
        }

    }

    private static void dfs(int x, int depth) {
        visit[x] = true;
        d[x] = depth;
        if (adj[x] != null) {
            for (int next : adj[x]) {
                if(visit[next]) continue;
                parent[next][0] = x;
                dfs(next, depth + 1);
            }
        }
    }

    private static void setParent() {
        for (int j = 1; j < MD; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }
    }

    private static int LCA(int x, int y) {
        if (d[x] > d[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        // depth
        for (int i = MD - 1; i >= 0; i--) {
            if (d[y] - d[x] >= (1 << i)) {
                y = parent[y][i];
            }
        }

        if(x == y) return x;

        // find parent
        for (int i = MD - 1; i >= 0; i--) {
            if (parent[x][i] != parent[y][i]) {
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        return parent[y][0];
    }

}
