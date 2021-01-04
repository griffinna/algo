package acmicpc;

import java.io.*;
import java.util.*;

public class Solution_1260 {

    static int[][] arr;
        static boolean[] visit;
        static StringBuffer sb;
        static int N;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            visit = new boolean[N + 1];
            arr = new int[N + 1][N + 1];
            sb = new StringBuffer();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a][b] = 1;
                arr[b][a] = 1;
            }
            dfs(V);
            sb.append("\n");
            Arrays.fill(visit, false);
            bfs(V);
        System.out.println(sb.toString());
    }

    static void dfs(int start) { // 깊이우선
        visit[start] = true;
        sb.append(start + " ");
        for (int i = 1; i <= N; i++) {
            int tmp = arr[start][i];
            if (tmp == 1 && !visit[i]) {
                dfs(i);
            }
        }
    }

    static void bfs(int start) { // 너비우선 (Queue)
        Queue<Integer> queue = new LinkedList<Integer>();
        visit[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            sb.append(num + " ");
            for (int i = 1; i <= N; i++) {
                int tmp = arr[num][i];
                if (tmp == 1 && !visit[i]) {
                    queue.add(i);
                    visit[i] = true;
                }
            }

        }
    }


}
