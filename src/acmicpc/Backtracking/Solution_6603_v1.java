package acmicpc.Backtracking;

import java.io.*;
import java.util.*;

public class Solution_6603_v1 { /* 로또 */
    static int K;
    static int[] list;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0) break;

            list = new int[K];
            visit = new boolean[K];
            for (int i = 0; i < K ; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0);
            System.out.println();
        }
    }

    private static void dfs(int start, int depth) {
        if(depth == 6) {
            for (int i = 0; i < K; i++) {
                if(visit[i]) System.out.print(list[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < K; i++) {
           visit[i] = true;
           dfs(i + 1, depth + 1);
           visit[i] = false;
        }
    }
}

