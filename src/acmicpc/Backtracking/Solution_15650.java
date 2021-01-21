package acmicpc.Backtracking;

import java.io.*;
import java.util.*;

public class Solution_15650 {
    static int[] arr;
    static boolean[] visit;
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        arr = new int[M + 1];
        int idx = 1;
        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                arr[idx] = i;
                visit[i] = true;
                dfs(arr, idx);

            }
        }

    }

    private static void dfs(int[] arr, int idx) {
        if(idx == M) {
            for (int j = 1; j <= M; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = arr[idx] + 1; i <= N; i++) {
            if (!visit[i]) {
                arr[idx + 1] = i;
                visit[i] = true;
                dfs(arr, idx + 1);
                visit[i] = false;
            }
        }
    }
}
