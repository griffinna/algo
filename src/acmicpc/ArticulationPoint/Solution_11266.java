package acmicpc.ArticulationPoint;

import java.io.*;
import java.util.*;

public class Solution_11266 {

    static ArrayList<Integer>[] adj;
    static int[] index;
    static boolean[] isJoint;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        adj = new ArrayList[V + 1];
        index = new int[V + 1];
        isJoint = new boolean[V + 1];
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
        for (int i = 1; i <= V; i++) {
            if(index[i] == 0) dfs(i, true);
        }
        StringBuffer sb = new StringBuffer();
        int jointCnt = 0;
        for (int i = 1; i < isJoint.length; i++) {
            if(isJoint[i]) {
                sb.append(i + " ");
                jointCnt++;
            }
        }
        System.out.println(jointCnt);
        System.out.println(sb.toString());
    }

    private static int dfs(int idx, boolean isRoot) {
        index[idx] = cnt++;
        int min = index[idx];
        int child = 0;
        if (adj[idx] != null) {
            for (int next: adj[idx]) {
                if (index[next] == 0) {
                    child++;
                    int low = dfs(next, false);
                    min = Math.min(min, low);
                    if (!isRoot && low >= index[idx]) isJoint[idx] = true;
                } else {
                    min = Math.min(min, index[next]);
                }
            }
        }
        if (isRoot && child >= 2) {
            isJoint[idx] = true;
        }
        return min;
    }
}
