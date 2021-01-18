package acmicpc.Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6603_v2 { /* 로또 */
    static int K;
    static int[] list;
    static int[] ans;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0) break;

            list = new int[K + 1];
            visit = new boolean[K + 1];
            ans = new int[7];
            for (int i = 1; i <= K ; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= K; i++) {
                ans[1] = list[i];
                visit[i] = true;
                dfs(ans, 1);
            }
            System.out.println();
        }
    }

    private static void dfs(int[] arr, int size) {
        if(size == 6) {
            for (int i = 1; i <= 6; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= K; i++) {
            if (!visit[i] && list[i] > ans[size]) {
                ans[size + 1] = list[i];
                visit[i] = true;
                dfs(ans, size + 1);
                visit[i] = false;
            }
        }
    }
}

