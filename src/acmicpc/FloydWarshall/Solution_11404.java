package acmicpc.FloydWarshall;

import java.io.*;
import java.util.*;

public class Solution_11404 {
    static int N, M;
    static int[][] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];

        StringTokenizer st;
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            dist[a][b] = (dist[a][b] > 0) ? Math.min(dist[a][b], c) : c;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N ; i++) {
                if(dist[i][k] == 0) continue;
                for (int j = 1; j <= N; j++) {
                    if(i == j || dist[k][j] == 0) continue;
                    if(dist[i][j] == 0 || dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(dist[i][j] + " ");
            }
            if(i != N) System.out.println();
        }

    }
}
