package acmicpc.Dijkstra;

import java.io.*;
import java.util.*;

public class Solution_4485 { /** https://www.acmicpc.net/problem/4485 */
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
                    map[i][j] = Integer.parseInt(st.nextToken());       // 위치에서 잃게되는 도둑루피 정보 저장
                    cost[i][j] = Integer.MAX_VALUE;                     // 해당 위치까지 오면서 잃게되는 가장 적은 금액 저장을 위해 최대값으로 초기화
                }
            }
            // 잃는 금액이 적은 순서대로 정렬되도록 우선순위큐 생성
            PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.cost - o2.cost;
                }
            });

            cost[0][0] = map[0][0]; // 시작지점에서 잃는 금액 == 시작지점에 있는 도둑루피 금액
            Node start = new Node();
            start.x = 0;
            start.y = 0;
            start.cost = cost[0][0];
            pq.add(start);          // 시작지점 정보를 큐에 저장

            int nextI, nextJ;
            while (!pq.isEmpty()) {     // 큐가 완전히 비워지지않았으면
                Node node = pq.poll();  // 가장 적게 잃는 금액이 있는 위치 정보를 꺼낸다.
                for (int i = 0; i < 4; i++) {   // 상 하 좌 우 4방향에 대해
                    nextI = node.x + dx[i][0];  // 다음위치의 i 를 구한다
                    nextJ = node.y + dx[i][1];  // 다음위치의 j 를 구한다
                    if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < N) {   // 다음위치가 전체 영역에서 벗어나지 않는 범위에 있는지 확인
                        // 이전에 다음위치까지 가면서 잃은 금액보다 (현위치까지 오면서 잃은 금액) + (다음위치에서 잃게 될 금액) 이 더 작으면
                        if (cost[nextI][nextJ] > cost[node.x][node.y] + map[nextI][nextJ]) {
                            cost[nextI][nextJ] = cost[node.x][node.y] + map[nextI][nextJ];  // 다음위치까지 가면서 잃을 금액 정보 갱신
                            Node next = new Node();
                            next.x = nextI;
                            next.y = nextJ;
                            next.cost = cost[nextI][nextJ];
                            pq.add(next);   // 다음위치 좌표정보와, 잃을 금액 정보를 큐에 넣는다.
                        }
                    }
                }
            }
            System.out.println("Problem " + T + ": " + cost[N - 1][N - 1]); // 도착지점까지 진행하면서 잃을 금액 정보 출력
        }
    }
}
