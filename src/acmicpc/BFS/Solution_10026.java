package acmicpc.BFS;

import java.io.*;
import java.util.*;

public class Solution_10026 {

    static int N;
    static int[][] map, tmp;
    static int[][] dx = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int normal, dis;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = stringToNumber(info[j], true);
                tmp[i][j] = stringToNumber(info[j], false);
            }
        }
        normal = 0;
        dis = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0) {
                    findArea(map, i, j);
                    normal++;
                }
                if(tmp[i][j] != 0) {
                    findArea(tmp, i, j);
                    dis++;
                }
            }
        }
        System.out.println(normal + " " + dis);
    }

    static void findArea(int[][] arr, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int val = arr[info[0]][info[1]];
            arr[info[0]][info[1]] = 0;
            for (int k = 0; k < 4; k++) {
                int nextI = info[0] + dx[k][0];
                int nextJ = info[1] + dx[k][1];
                if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < N && arr[nextI][nextJ] != 0) {
                    if(val == arr[nextI][nextJ]) queue.add(new int[]{nextI, nextJ});
                }
            }
        }
    }

    static int stringToNumber(String str, boolean isNormal) {
        if (str.equals("R")) {
            return 1;
        } else if (str.equals("B")) {
            return 2;
        } else {
            if(!isNormal) return 1;
            return 3;
        }
    }

}
