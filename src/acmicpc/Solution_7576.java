package acmicpc;

import java.util.*;
import java.io.*;

public class Solution_7576 {

    static int[][] dx = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int N, M, cnt, days;
    static int[][] box;
    static Queue<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] != -1) cnt++;
                if (box[i][j] == 1) {
                    queue.add(new int[]{i, j, 1});
                }
            }
        }
        int ans = bfs() - 1;
        if(cnt > 0) ans = -1;
        System.out.println(ans);
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nextI, nextJ;
            days = now[2];
            cnt--;
            for (int i = 0; i < 4; i++) {
                nextI = now[0] + dx[i][0];
                nextJ = now[1] + dx[i][1];
                if (0 <= nextI && 0 <= nextJ && nextI <= N - 1 && nextJ <= M - 1
                        && box[nextI][nextJ] != -1 && (box[nextI][nextJ] > days + 1 || box[nextI][nextJ] == 0)) {
                    queue.add(new int[]{nextI, nextJ, days + 1});
                    box[nextI][nextJ] = days + 1;
                }
            }
        }
        return days;
    }
}
