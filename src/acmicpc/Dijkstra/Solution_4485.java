package acmicpc.Dijkstra;

import java.io.*;
import java.util.*;

public class Solution_4485 {
    static class Node {int x, y, cost;}
    static int[][] dx = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        int[][] map;
        int[][] cost;
        StringTokenizer st;
        int T = 0;
        while(true){
            N = Integer.parseInt(br.readLine());
            T++;
            if(N == 0) break;
            map = new int[N][N];
            cost = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    cost[i][j] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.cost - o2.cost;
                }
            });

            cost[0][0] = map[0][0];
            Node start = new Node();
            start.x = 0;
            start.y = 0;
            start.cost = cost[0][0];
            pq.add(start);

            int nextI, nextJ;
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                for (int i = 0; i < 4; i++) {
                    nextI = node.x + dx[i][0];
                    nextJ = node.y + dx[i][1];
                    if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < N) {
                        if (cost[nextI][nextJ] > cost[node.x][node.y] + map[nextI][nextJ]) {
                            cost[nextI][nextJ] = cost[node.x][node.y] + map[nextI][nextJ];
                            Node next = new Node();
                            next.x = nextI;
                            next.y = nextJ;
                            next.cost = cost[nextI][nextJ];
                            pq.add(next);
                        }
                    }
                }
            }
            System.out.println("Problem " + T + ": " + cost[N - 1][N - 1]);
        }
    }
}
