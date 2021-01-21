package acmicpc.DFS;

import java.io.*;
import java.util.*;

public class Solution_4963 {
    static int W, H; // < 50
    static int[][] map;
    static int[][] dx = {{-1, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if(W == 0 && H == 0) break;

            map = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == 1) {
                        cnt++;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    private static void dfs(int i, int j) {
        map[i][j] = 0;
        int nextI, nextJ;
        for (int k = 0; k < dx.length; k++) {
            nextI = i + dx[k][0];
            nextJ = j + dx[k][1];
            if (nextI >= 0 && nextJ >= 0 && nextI < H && nextJ < W
                    && map[nextI][nextJ] == 1) {
                dfs(nextI, nextJ);
            }
        }
    }
}
