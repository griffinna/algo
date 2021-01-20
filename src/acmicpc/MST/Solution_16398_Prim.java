package acmicpc.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_16398_Prim {
    static int N;
    static ArrayList<Road>[] adj;
    static boolean[] visit;
    static class Road {
        int idx, w;
        public Road(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        visit = new boolean[N];
        PriorityQueue<Road> pq = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o1.w - o2.w;
            }
        });
        int n;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                n = Integer.parseInt(st.nextToken());
                if (n != 0 && i != j) {
                    if(adj[i] == null) adj[i] = new ArrayList<>();
                    adj[i].add(new Road(j, n));
                }
            }
        }
        long sum = 0;
        int cnt = 0;
        pq.add(new Road(0, 0));
        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if(visit[now.idx]) continue;
            sum += now.w;
            cnt++;
            visit[now.idx] = true;
            if(cnt == N){
                break;
            }
            if (adj[now.idx] != null) {
                for (Road next: adj[now.idx]) {
                    if (!visit[next.idx]) {
                        pq.add(next);
                    }
                }
            }
        }
        System.out.println(sum);

    }

}
