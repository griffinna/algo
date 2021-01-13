package acmicpc.Dijkstra;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Solution_1854 {
    static class Info { int idx, t; }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Info>[] adj = new ArrayList[N + 1];
        PriorityQueue<Integer>[] time = new PriorityQueue[N + 1];
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            Info info = new Info();
            info.idx = b;
            info.t = c;
            if(adj[a] == null) adj[a] = new ArrayList<>();
            adj[a].add(info);
        }
        for (int i = 1; i <= N; i++) {
            time[i] = new PriorityQueue<>(K, Comparator.reverseOrder());
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.add(new int[]{1, 0});        // 1번 출발
        time[1].add(0);
        int nowIdx, nowCost, nextIdx;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            nowIdx = now[0];
            nowCost = now[1];
            if (adj[nowIdx] != null) {
                for (Info next: adj[nowIdx]) {
                    nextIdx = next.idx;
                    if(time[nextIdx].size() < K){
                        time[nextIdx].add(nowCost + next.t);
                        pq.add(new int[]{nextIdx, nowCost + next.t});
                    }else if(time[nextIdx].peek() > nowCost + next.t) {
                        time[nextIdx].poll();
                        time[nextIdx].add(nowCost + next.t);
                        pq.add(new int[]{nextIdx, nowCost + next.t});
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if(time[i].size() == K) {
                System.out.println(time[i].peek());
            }else {
                System.out.println(-1);
            }
        }

    }
}
