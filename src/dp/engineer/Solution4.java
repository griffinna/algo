package dp.engineer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution4 {

	static int N, M, K;
	static int[][] factory;
	static int[][] worker;
	static int[][] result;
	static int[][] dp;
	static int max;
	static int maxStatus;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("D:/dev/sw/engineer_sample.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			factory = new int[M + 1][N + 1];
			worker = new int[K][N + 1];
			result = new int[K][N + 1];
			boolean[] visit = new boolean[K];
			max = 0;
			maxStatus = 1 << K;
			dp = new int[maxStatus][N + 1];
			st = new StringTokenizer(br.readLine());
			char[] workerArr = st.nextToken().toCharArray();
			for (int i = 0; i < K; i++) {
				worker[i][0] = workerArr[i] - 64;
			}
			
			String word;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				word = "0" + st.nextToken();
				char[] charArr = word.toCharArray();
				Arrays.setAll(factory[i], j -> ((int) charArr[j]) - 64);
			}
			
			for (int i = 0; i < K; i++) {
				int n = worker[i][0];
				for (int j = 1; j <= N; j++) {
					int cnt = 0;
					for (int k = 1; k <= M; k++) {
						if(factory[k][j] == n) cnt++;
					}
					worker[i][j] = cnt;
				}
			}
			
			int answer = 0;
			for(int i = 0; i < K; i++) {
				Arrays.fill(visit, false);
				visit[i] = true;
				int status = change(visit);
				dp[status][1] = worker[i][1];
				if(N > 1) {
//					System.out.println("-- dp["+status+"]["+1+"] : " + dp[status][1]);
					solve(status, status, i, 2, visit);
				}else {
					for (int j = 0; j < K; j++) {
						max += worker[j][1];
					}
				}
//				System.out.println("mmmm : " + max);
				answer = Math.max(answer, max);
			} 
			System.out.println("#" + test_case + " " + answer);
		}
		
	}

	private static void solve(int prevStatus, int status, int empIdx, int unitIdx, boolean[] visit) {
		dp[status][unitIdx] = Math.max(dp[status][unitIdx], dp[prevStatus][unitIdx - 1] + worker[empIdx][unitIdx]);
		if(unitIdx == N) {
			max = Math.max(max, dp[status][unitIdx]);
		}
		for (int i = 0; i < K; i++) {
			int nowStatus = status;
			if(!visit[i] || i == empIdx) {
				visit[i] = true;
				status = change(visit);
			}
			for (int j = unitIdx; j < N; j++) {
				solve(nowStatus, status, i, j + 1, visit);
			}
			visit[i] = false;
		}
	}
	
	static int change(boolean[] arr) {
		String status = "";
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]) {
				status += "1";
			}else {
				status += "0";
			}
		}
		return Integer.valueOf(status, 2);
	}
	
}
