package acmicpc.BFS;

import java.io.*;
import java.util.*;

public class Solution_2606 {

    static int N, M;
    static int CNT;
    static boolean[] visit;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        CNT = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(list[a] == null) list[a] = new ArrayList<>();
            if(list[b] == null) list[b] = new ArrayList<>();
            list[a].add(b);
            list[b].add(a);
        }

        bfs(1);
        System.out.println(CNT);
    }

    private static void bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visit[i] = true;
        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (list[num] != null) {
                for (int next: list[num]) {
                    if (!visit[next]) {
                        queue.add(next);
                        visit[next] = true;
                        CNT++;
                    }
                }
            }
        }
    }
}
