package acmicpc.BFS;

import java.io.*;
import java.util.*;

public class Solution_1012 {

    static int M, N, K, X, Y;
    static int[][] map;
    static int[][] dx = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int ANS;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            ANS = 0;
            int a, b;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                map[b][a] = 1;
            }

            loop : for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        findNext(i, j, 0);
                        if(ANS == K) break loop;
                    }
                }
            }
            System.out.println(ANS);
        }
    }
    static int findNext(int i, int j, int cnt){
        if(cnt == 0) {
            ANS++;
        }
        map[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int nextI = i + dx[k][0];
            int nextJ = j + dx[k][1];
            if(nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M
                    && map[nextI][nextJ] == 1){
                findNext(nextI, nextJ, ++cnt);
            }
        }
        return cnt;
    }

}
