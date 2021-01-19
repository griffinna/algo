package acmicpc.Dijkstra;

import java.io.*;
import java.util.*;

public class Solution_10282 {
    static int N, D, C;
    static ArrayList<Info>[] adj;
    static int[] time;
    static boolean[] visit;
    static class Info {
        int idx, s;
        public Info(int idx, int s) {
            this.idx = idx;
            this.s = s;
        }
    }

    static class InfoSort implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.s - o2.s;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            adj = new ArrayList[N + 1];
            time = new int[N + 1];
            visit = new boolean[N + 1];
            Arrays.fill(time, Integer.MAX_VALUE);
            int a, b, c;
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                if (adj[b] == null) adj[b] = new ArrayList<>();
                adj[b].add(new Info(a, c));
            }

            PriorityQueue<Info> pq = new PriorityQueue<>(new InfoSort());
            pq.add(new Info(C, 0));
            time[C] = 0;
            int cnt = 0;
            int lastTime = 0;
            while (!pq.isEmpty()) {
                Info now = pq.poll();
                if (!visit[now.idx]) {
                    cnt++;
                    lastTime = Math.max(time[now.idx], lastTime);
                    visit[now.idx] = true;
                }

                if (adj[now.idx] != null) {
                    for (Info next: adj[now.idx]) {
                        if (time[next.idx] > time[now.idx] + next.s) {
                            time[next.idx] = time[now.idx] + next.s;
                            pq.add(new Info(next.idx, time[next.idx]));
                        }
                    }
                }
            }
            sb.append(cnt + " " + lastTime + "\n");
        }
        System.out.println(sb.toString());
    }
}
