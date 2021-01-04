package acmicpc;

import java.io.*;
import java.util.*;

public class Solution_2667 {

    static int N;
    static int[][] map, vilage;
    static boolean[][] visit;
    static ArrayList<Integer> cntList;
    static int cnt;
    static int[][] dx = {{-1, 0}, {0 , -1}, {1, 0}, {0, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        vilage = new int[N][N];
        visit = new boolean[N][N];
        cntList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(info[j]);
            }
        }

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && map[i][j] == 1) {
                    cntList.add(dfs(i, j, 0));
                }
            }
        }

        System.out.println(cnt);
        Collections.sort(cntList);
        for (int n: cntList) {
            System.out.println(n);
        }
    }

    private static int dfs(int i, int j, int vCnt) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        vilage[i][j] = cnt++;
        visit[i][j] = true;
        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            vCnt++;
            for (int k = 0; k < dx.length; k++) {
                int nextX = info[0] + dx[k][0];
                int nextY = info[1] + dx[k][1];
                if (nextX >= 0 && nextY >= 0 && nextX <= N - 1 && nextY <= N - 1
                        && !visit[nextX][nextY] && map[nextX][nextY] == 1) {
                    vilage[nextX][nextY] = vilage[info[0]][info[1]];
                    queue.add(new int[]{nextX, nextY});
                    visit[nextX][nextY] = true;
                }
            }
        }
        return vCnt;
    }
}
