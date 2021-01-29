package acmicpc.DFS;

import java.io.*;
import java.util.*;

public class Solution_test {
    static int[][] map;
    static int[][] dx = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int max, H, W;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
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
                    max = Math.max(dfs(i, j, 1), max);
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }

    private static int dfs(int i, int j, int cnt) {
        map[i][j] = 0;
        int nextI, nextJ;
        for (int k = 0; k < 4; k++) {
            nextI = i + dx[k][0];
            nextJ = j + dx[k][1];
            if (nextI >= 0 && nextJ >= 0 && nextI < H && nextJ < W
                    &&map[nextI][nextJ] == 1) {
                cnt = dfs(nextI, nextJ, cnt + 1);
            }
        }
        return cnt;
    }
}
