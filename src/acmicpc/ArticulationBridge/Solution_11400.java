package acmicpc.ArticulationBridge;

import java.io.*;
import java.util.*;

public class Solution_11400 {   /* 단절선 */
    static ArrayList<int[]> isCut = new ArrayList<>();
    static int[] index;
    static ArrayList<Integer>[] adj;
    static int cnt;

    static class SortArray implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] - o2[0] < 0) {
                return -1;
            } else if (o1[0] - o2[0] > 0) {
                return 1;
            } else {
                if (o1[1] - o2[1] < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        index = new int[V + 1];
        adj = new ArrayList[V + 1];
        int a, b;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(adj[a] == null) adj[a] = new ArrayList<>();
            if(adj[b] == null) adj[b] = new ArrayList<>();
            adj[a].add(b);
            adj[b].add(a);
        }
        cnt = 1;
        dfs(1, 0);
        System.out.println(isCut.size());
        Collections.sort(isCut, new SortArray());
        int s = 0;
        int e = 0;
        for (int i = 0; i < isCut.size(); i++) {
            if (s != isCut.get(i)[0] || e != isCut.get(i)[1]) {
                s = isCut.get(i)[0];
                e = isCut.get(i)[1];
                System.out.println(s + " " + e);
            }
        }
    }

    private static int dfs(int now, int parent) {
        index[now] = cnt++;
        int ret = index[now];
        if (adj[now] != null) {
            for (int next: adj[now]) {
                // 부모로 가는 간선은 제외
                if (next == parent) continue;
                if (index[next] > 0) {
                    ret = Math.min(ret, index[next]);
                    continue;
                }
                int prev = dfs(next, now);
                if (prev > index[now]) {
                    isCut.add(new int[]{Math.min(now, next), Math.max(now, next)});
                }
                ret = Math.min(ret, prev);
            }
        }
        return ret;
    }
}

