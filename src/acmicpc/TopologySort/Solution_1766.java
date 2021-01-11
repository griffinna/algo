package acmicpc.TopologySort;

import java.io.*;
import java.util.*;

public class Solution_1766 {        // 위상정렬 - PriorityQueue

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N + 1];
        boolean[] visit = new boolean[N + 1];
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            indegree[b]++;
            if(adj[a] == null) adj[a] = new ArrayList<>();
            adj[a].add(b);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            visit[now] = true;
            sb.append(now + " ");
            if (adj[now] != null) {
                for (int next: adj[now]) {
                    if (!visit[next]) {
                        indegree[next]--;
                        if (indegree[next] == 0) {
                            pq.add(next);
                        }
                    }
                }
            }
        }
        System.out.println(sb.toString());

    }
}
