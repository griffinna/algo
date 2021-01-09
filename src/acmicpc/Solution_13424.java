package acmicpc;

import java.io.*;
import java.util.*;

public class Solution_13424 {

    static int N;   // 방의 개수
    static int M;   // 비밀통로의 개수
    static int K;   // 친구 수
    static int[][] map;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N + 1][N + 1];
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (i == j) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = INF;
                    }
                }
            }
            int a, b, c;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                map[a][b] = c;
                map[b][a] = c;
            }
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if(map[i][k] != INF && map[k][j] != INF
                                && map[i][j] > map[i][k] + map[k][j]) {
                            map[i][j] = map[i][k] + map[k][j];
                        }
                    }
                }
            }
            K = Integer.parseInt(br.readLine());
            int minRoomIdx = 0;
            int min = INF;
            int[] room = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                int n = Integer.parseInt(st.nextToken());
                for (int j = 1; j <= N; j++) {
                    room[j] += map[n][j];
                    if(i == K - 1) {
                        if(min > room[j]) {
                            min = room[j];
                            minRoomIdx = j;
                        }
                    }
                }
            }
            System.out.println(minRoomIdx);
        }
    }

}
