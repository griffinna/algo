package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS_dp_Solution {

    static int[] arr;   // index 마다 각 입력값
    static int[] dp;    // index 마다 각 증가 수열의 길이
    static int max = 0;
    static int N;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        // index 0 ~ N - 1
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp[j] + 1 > dp[i]){
                    // 증가수열
                    dp[i] = dp[j] + 1;
                }
            }

            if (max < dp[i]) {
                max = dp[i];
            }
        }
        System.out.println(dp[N-1]);
    }
}
