package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 첫 번째 줄에 그래프의 정점의 개수 V, 간선의 개수 E, 그리고 시작 정점의 번호 S가 공백으로 분리되어 주어진다. (1 ≤ S ≤ V ≤ 20,000, 1 ≤ E ≤ 100,000)
 두 번째 줄부터 E개의 줄에 걸쳐 각 간선의 정보인 x, y가 공백으로 분리되어 주어진다. 이는 x와 y를 잇는 간선이 존재한다는 것을 의미한다. (1 ≤ x, y ≤ V, x ≠ y)
 정점 S에서 시작한 너비 우선 탐색의 결과 중 오름차순으로 가장 빠른 것을 출력한다.

 입력 예제
 5 6 2
 1 2
 1 3
 2 4
 3 4
 3 5
 4 5

 출력
 2 1 4 3 5
 */
public class BFS {
    static int V;   // node count
    static int E;   // edge count
    static int S;   // start node
    static int x, y;

    static ArrayList<Integer>[] graph;
    static ArrayList<Integer> bfs;

    static boolean[] visited;

    static Queue<Integer> queue;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        graph = new ArrayList[E + 1];

        // 초기화
        Arrays.fill(graph, new ArrayList<Integer>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            // 무방향그래프
            graph[x].add(y);
            graph[y].add(x);
        }

        for (ArrayList<Integer> arr : graph) {
            Collections.sort(arr);
        }

        bfsSolution();

        for (int i = 0; i < bfs.size(); i++) {
            System.out.print(bfs.get(i) + " ");
        }

    }

    private static void bfsSolution() {
        bfs = new ArrayList<>();
        visited = new boolean[E + 1];
        queue = new LinkedList<>();

        // start node 를 queue 에 add
        queue.add(S);
        // visit start node
        visited[S] = true;

        while (!queue.isEmpty()) {
            // 1. queue 에 node add
            int node = queue.poll();
            bfs.add(node);
            // 2. 이동한 정점에서 연결된 정점들을 큐에 넣고 visited 체크
            for (int i : graph[node]) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }

        }

    }

}
