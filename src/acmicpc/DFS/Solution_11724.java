package acmicpc.DFS;

import java.io.*;
import java.util.*;

public class Solution_11724 {       /* 연결 요소의 개수 */
    static boolean[] visit;
    static int N, M;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(list[a] == null) list[a] = new ArrayList<>();
            if(list[b] == null) list[b] = new ArrayList<>();
            list[a].add(b);
            list[b].add(a);
        }
        int ANS = 0;
        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                ANS++;
                dfs(i);
            }
        }
        System.out.println(ANS);
    }

    private static void dfs(int i) {
        if (list[i] != null) {
            for (int next: list[i]) {
                if (!visit[next]) {
                    visit[next] = true;
                    dfs(next);
                }
            }
        }
    }
}
