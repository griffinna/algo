package acmicpc.TopologySort;

import java.io.*;
import java.util.*;

public class Solution_2252 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[N + 1];
        boolean[] visit = new boolean[N + 1];
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(adj[a] == null) adj[a] = new ArrayList<>();
            adj[a].add(b);
            indegree[b]++;
        }

        for (int i = 1; i < indegree.length; i++) {
            if(indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now + " ");
            visit[now] = true;
            if (adj[now] != null) {
                for (int next: adj[now]) {
                    if(!visit[next]) {
                        indegree[next]--;
                        if(indegree[next] == 0) queue.add(next);
                    }
                }
            }
        }

        System.out.println(sb.toString());


    }
}
