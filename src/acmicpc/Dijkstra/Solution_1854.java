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

        ArrayList<Info>[] adj = new ArrayList[N + 1];               // 정점에서 연결된 다른 정점 정보를 저장하는 list
        PriorityQueue<Integer>[] time = new PriorityQueue[N + 1];   // 정점들까지의 K번째 최단거리를 찾기 위해 정점마다 우선순위큐를 생성
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            Info info = new Info();
            info.idx = b;
            info.t = c;
            if(adj[a] == null) adj[a] = new ArrayList<>();      // 정점들의 연결정보를 저장한다
            adj[a].add(info);
        }
        for (int i = 1; i <= N; i++) {          // 정점수만큼 반복하며
            time[i] = new PriorityQueue<>(K, Comparator.reverseOrder());    // 정점마다 내림차순으로 정렬되는 우선순위큐를 생성한다 (사이즈는 K로 고)
        }

        // [0] : 정점번호 , [1] : 1부터 정점까지의 거리 (거리 기준으로 오름차순 정렬)
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.add(new int[]{1, 0});        // 1번 출발
        time[1].add(0);                 // 1 > 1 로 가는 1순위 최단경로 저장
        int nowIdx, nowCost, nextIdx;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            nowIdx = now[0];            // 현재 인덱스
            nowCost = now[1];           // 현재 인덱스까지의 거리정
            if (adj[nowIdx] != null) {  // now 에서 연결된 정점이 있다면
                for (Info next: adj[nowIdx]) {
                    nextIdx = next.idx;         // 연결된 정점 next 에 대해 탐색을 시작한다.
                    if(time[nextIdx].size() < K){       // 만약 next 까지 가는 최단경로가 K개 이하라면
                        time[nextIdx].add(nowCost + next.t);            // (now 까지의 거리) + (now 에서 next 로 가는 거리)를 더해서 next 의 최단경로 우선순위큐에 넣는다.
                        pq.add(new int[]{nextIdx, nowCost + next.t});   // (now 까지의 거리) + (now 에서 next 로 가는 거리)를 더해서 우선순위 큐에 넣는다.
                    }else if(time[nextIdx].peek() > nowCost + next.t) { // K번째 경로가 (now 까지의 거리) + (now 에서 next 로 가는 거리) 보다 멀다면
                        time[nextIdx].poll();                           // 기존 K번째 경로정보는 뽑아서 버리고
                        time[nextIdx].add(nowCost + next.t);            // (now 까지의 거리) + (now 에서 next 로 가는 거리) 를 next 의 최단경로 우선순위큐에 넣는다.
                        pq.add(new int[]{nextIdx, nowCost + next.t});   // (now 까지의 거리) + (now 에서 next 로 가는 거리)를 더해서 우선순위 큐에 넣는다.
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if(time[i].size() == K) {       // 정점까지의 거리정보가 K 개가 있다면
                System.out.println(time[i].peek()); // K번째 거리정보를 출력
            }else {                         // 정점까지의 거리정보가 K 개가 안되면
                System.out.println(-1);     // -1 을 출력
            }
        }

    }
}
