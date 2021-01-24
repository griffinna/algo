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
            // 다른 노드에서 들어오는 간선이 몇개인지 카운트한다.
            indegree[b]++;
        }

        // 다른 노드에서 들어오는 간선이 없으면 (indegree[i] == 0) 출발점이므로 큐에 넣어준다.
        for (int i = 1; i < indegree.length; i++) {
            if(indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();     // 큐에서 정점을 꺼내서
            sb.append(now + " ");
            visit[now] = true;          // 해당 정점은 지나갔음을 표시하고
            if (adj[now] != null) {     // 연결된 다른 정점이 있다면
                for (int next: adj[now]) {
                    if(!visit[next]) {      // 정점들 중에 아직 방문하지 않은 정점의
                        indegree[next]--;   // indegree 를 -1 해준다 (now 정점이 next 정점으로 들어갔으니 들어오는 간선 -1)
                        if(indegree[next] == 0) queue.add(next);    // 더 이상 들어오는 간선이 없다면 큐에 넣어준다.
                    }
                }
            }
        }

        System.out.println(sb.toString());


    }
}
