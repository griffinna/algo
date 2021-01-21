package acmicpc.LCA;

import java.io.*;
import java.util.*;

public class Solution_3176 { /* https://devowen.com/274 */
    static int N, MD;
    static int[][] parent, maxP, minP;
    static int[] d;
    static ArrayList<Info>[] adj;
    static boolean[] visit;
    static class Info {
        int idx, w;
        public Info(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        MD = 0;
        while (Math.pow(2, MD) < N) {
            MD++;
        }
        parent = new int[N + 1][MD];
        maxP = new int[N + 1][MD];
        minP = new int[N + 1][MD];
        d = new int[N + 1];
        adj = new ArrayList[N + 1];
        visit = new boolean[N + 1];

        int a, b, c;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(adj[a] == null) adj[a] = new ArrayList<>();
            if(adj[b] == null) adj[b] = new ArrayList<>();
            adj[a].add(new Info(b, c));
            adj[b].add(new Info(a, c));
        }
        // dfs
        dfs(1, 0);

        // setParent
        for (int j = 1; j < MD; j++) {
            for (int i = 1; i <= N; i++) {
                maxP[i][j] = Math.max(maxP[i][j - 1], maxP[parent[i][j -1]][j -1]);
                minP[i][j] = Math.min(minP[i][j - 1], minP[parent[i][j -1]][j -1]);
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int[] res = LCA(a, b);
            System.out.println(res[0] + " " + res[1]);
        }

    }

    static int[] LCA(int x, int y){
        if (d[x] > d[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = MD - 1; i >= 0 ; i--) {
            if (d[y] - d[x] >= (1 << i)) {
                min = Math.min(min, minP[y][i]);
                max = Math.max(max, maxP[y][i]);
                y = parent[y][i];
            }
        }

        if(x == y) return new int[]{min, max};

        for (int i = MD - 1; i >= 0; i--) {
            if (parent[x][i] != parent[y][i]) {
                max = Math.max(max, Math.max(maxP[x][i], maxP[y][i]));
                min = Math.min(min, Math.min(minP[x][i], minP[y][i]));
                x = parent[x][i];
                y = parent[y][i];
            }
        }
        max = Math.max(max, Math.max(maxP[y][0], maxP[x][0]));
        min = Math.min(min, Math.min(minP[y][0], minP[x][0]));
        return new int[]{min, max};
    }

    static void dfs(int idx, int depth) {
        d[idx] = depth;
        visit[idx] = true;
        if (adj[idx] != null) {
            for (Info next: adj[idx]) {
                if(visit[next.idx]) continue;
                parent[next.idx][0] = idx;
                maxP[next.idx][0] = next.w;
                minP[next.idx][0] = next.w;
                dfs(next.idx, depth + 1);
            }
        }
    }

}
