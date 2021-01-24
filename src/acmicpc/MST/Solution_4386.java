package acmicpc.MST;

import java.io.*;
import java.util.*;

public class Solution_4386 {
    static class Edge { int s, e; double dist; }
    static class Star { int idx; double x, y; }

    // 간선을 가중치 오름차순으로 정렬하기 위한 class
    static class SortEdge implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            if(o1.dist <= o2.dist) return -1;
            return 1;
        }
    }

    static int[] parent, rank;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Star[] list = new Star[N];
        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Star star = new Star();
            star.idx = i + 1;
            star.x = Double.parseDouble(st.nextToken());
            star.y = Double.parseDouble(st.nextToken());
            parent[star.idx] = star.idx;
            list[i] = star;
        }

        // 간선을 가중치 오름차순으로 정렬해서 사용 할 우선순위 큐 선언
        PriorityQueue<Edge> edgePq = new PriorityQueue<>(new SortEdge());

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int next = j;
                Edge edge = new Edge();
                edge.s = list[i].idx;
                edge.e = list[next].idx;
                edge.dist = getDistance(list[i].x, list[i].y, list[next].x, list[next].y);
                edgePq.add(edge);   // 간선정보를 우선순위큐에 넣어준다.
            }
        }

        int cnt = 0;
        double sum = 0;
        while (!edgePq.isEmpty()) {         // 큐에서 가중치가 작은 간선부터 연결을 시도한다.
            Edge now = edgePq.poll();
            if (!isConnect(now.s, now.e)) { // 해당 간선의 양끝 정점이 이어져있지않다면
                union(now.s, now.e);        // 두 정점을 연결시킨다.
                cnt++;                      // 연결 간선수를 + 1
                sum += now.dist;            // 연결된 간선 거리를 더해준다.
            }

            if(cnt == N - 1) break;         // 연결된 간선수가 정점수 -1 이라면 모든 정점이 연결됐기때문에 탐색 종료
        }
        System.out.println(String.format("%.2f", sum));
    }

    private static boolean isConnect(int s, int e) {
        return findRoot(s) == findRoot(e);
    }

    private static void union(int n, int m) {
        n = findRoot(n);
        m = findRoot(m);
        if (rank[n] < rank[m]) {
            parent[n] = parent[m];
        } else {
            parent[m] = parent[n];
            if (rank[n] == rank[m]) {
                rank[n]++;
            }
        }
    }

    private static int findRoot(int n) {
        if(n == parent[n]) return n;
        return parent[n] = findRoot(parent[n]);
    }

    private static double getDistance(double x, double y, double x1, double y1) {
        double xd = Math.pow((x1 - x), 2);
        double yd = Math.pow((y1 - y), 2);
        return Math.sqrt(xd + yd);
    }

}
