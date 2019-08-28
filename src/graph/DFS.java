package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
입력 첫줄에는 정점의 개수, 간선의 개수, 탐색을 시작할 정점 이 3가지를 입력받고
나머지 2~5번째 줄은 간선과 연결된 정점들을 입력합니다.

입력값
5 4 5
5 4
4 3
4 2
1 5

출력값
5 4 3 2 1
 */
public class DFS {

    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    static int N;   // node count
    static int E;   // edge count
    static int S;   // start node
    static int x, y;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new ArrayList [N + 1];
        Arrays.fill(arr, new ArrayList<Integer>());

        visited = new boolean[N + 1];
        Arrays.fill(visited, false);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            // 무방향 그래프는 양쪽 다 add
            arr[x].add(y);
            arr[y].add(x);
        }

        dfsSolution(S);
    }

    private static void dfsSolution(int s) {

        visited[s] = true;
        System.out.print(s + " ");

        for (int i : arr[s]) {
            if (!visited[i]) {
                dfsSolution(i);
            }
        }
    }

}
