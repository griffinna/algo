package acmicpc;

/**문제
 N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다.
 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
 A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.

 입력
 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다.
 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다.
 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.

 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.

 5
 8
 1 2 2
 1 3 3
 1 4 1
 1 5 10
 2 4 2
 3 4 1
 3 5 1
 4 5 3
 1 5

 출력
 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 */

import java.io.*;
import java.util.*;

public class Solution_1916 {

    static int N;   // 도시
    static int M;   // 버스
    static int A, B;    // 출발, 도착
    static ArrayList<Edge>[] adj;
    static int[] cost;

    static class Edge implements Comparable<Edge>{
        int idx, cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = Integer.MAX_VALUE;
        }
        StringTokenizer st;
        int s, e, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(e, c);
            adj[s].add(edge);
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        cost[S] = 0;
        queue.add(new Edge(S, cost[S]));
        while (!queue.isEmpty()) {
            Edge now = queue.poll();
            if (now.idx == E) {
                System.out.println(now.cost);
                break;
            }
            for (Edge next: adj[now.idx]) {
                if (cost[next.idx] > cost[now.idx] + next.cost) {
                    cost[next.idx] = cost[now.idx] + next.cost;
                    queue.add(new Edge(next.idx, cost[next.idx]));
                }
            }
        }

    }

}
