package acmicpc.Combinatorics;

import java.io.*;
import java.util.*;

public class Solution_2775 {
    static int K, N;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        map = new int[15][15];
        init();
        for (int test_case = 1; test_case <= T; test_case++) {
            K = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());

            System.out.println(map[K][N]);

        }
    }

    private static void init() {
        for (int i = 0; i < 15; i++) {
            map[0][i] = i;
        }
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                map[i][j] = map[i][j - 1] + map[i - 1][j];
            }
        }
    }
}
