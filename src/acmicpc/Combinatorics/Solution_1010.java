package acmicpc.Combinatorics;

import java.io.*;
import java.util.*;

public class Solution_1010 {
    static int[][] arr = new int[31][31];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(combi(b, a));
        }
    }

    private static int combi(int a, int b) {
        if(a == b || b == 0) return arr[a][b] = 1;
        if(arr[a][b] > 0) return arr[a][b];
        return arr[a][b] = combi(a - 1, b - 1) + combi(a - 1, b);
    }
}
