package acmicpc.DFS;

import java.io.*;
import java.util.*;

public class Solution_2583 {
    static int N, M, K;
    static int[][] map;
    static int[][] dx = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        int x1, y1, x2, y2;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    map[j][k] = -1;
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0){
                    int cnt = dfs(i, j, 1);
                    ans.add(cnt);
                }
            }
        }
        Collections.sort(ans);
        System.out.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }

    private static int dfs(int i, int j, int cnt) {
        map[i][j] = 1;
        for (int k = 0; k < 4; k++) {
            int nextI = i + dx[k][0];
            int nextJ = j + dx[k][1];
            if (nextI >= 0 && nextJ >= 0 && nextI < M && nextJ < N && map[nextI][nextJ] == 0) {
                cnt = dfs(nextI, nextJ, cnt + 1);
            }
        }
        return cnt;
    }
}
