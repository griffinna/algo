package acmicpc.Backtracking;

import java.util.*;
import java.io.*;

public class Solution_15652 { /* N과 M*/
    static int[] arr;
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        find(0);
    }

    private static void find(int cnt) {
        if (cnt == M) {
            print();
            return;
        }
        int str = (cnt == 0) ? 1 : arr[cnt - 1];
        for (int i = str; i <= N; i++) {
            arr[cnt] = i;
            find(cnt + 1);
        }

    }

    private static void print() {
        for (int i = 0; i < M; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
