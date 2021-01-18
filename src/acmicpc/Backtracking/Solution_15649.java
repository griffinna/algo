package acmicpc.Backtracking;

import java.io.*;
import java.util.*;

public class Solution_15649 {
    static int N, M;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        int[] arr;
        for (int i = 1; i <= N; i++) {
            visit[i] = true;
            arr = new int[M + 1];
            arr[1] = i;
            dfs(arr, 1);
            visit[i] = false;
        }
    }

    private static void dfs(int[] arr, int size) {
        if(size == M){
            for (int i = 1; i <= M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[size + 1] = i;
                dfs(arr, size + 1);
                visit[i] = false;
            }
        }

    }
}
