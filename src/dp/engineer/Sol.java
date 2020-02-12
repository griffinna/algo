package dp.engineer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Sol {

	static int N, M, K;
	static int ANSWER;
	static int[][] plan;
	static int[][] max;
	static int[][] empArr;
	
	static HashMap<Integer, int[]> alphaCnt;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("D:/dev/sw/engineer_sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			plan = new int[27][N + 1];
			max = new int[N + 1][K + 1];
			alphaCnt = new HashMap<Integer, int[]>();
			
			empArr = new int[K + 1][3];	// 0 : alpha, 1 : now, status (0: false, 1 : true)
			
			ANSWER = 0;
			
			char[] empInfo = ("0" + br.readLine()).toCharArray();
			
			for (int i = 1; i <= M; i++) {
				char[] ItemInfo = ("0" + br.readLine()).toCharArray();
				for (int j = 1; j <= N; j++) {
					int aph = ((int) ItemInfo[j] - 64); 
					plan[aph][j]++;
				}
			}
			
			for (int i = 1; i <= K; i++) {
				int target = ((int) empInfo[i] - 64);
				empArr[i][0] = target;
				
				alphaCnt.put(target, plan[target]);
			}
			
			solve(1);
			
			System.out.println("#" + test_case + " " + ANSWER);
		}
	}
	
	private static void solve(int empNum) {
		
		if(empNum > K) {
			for (int j = 1; j <= K; j++) {
				for (int i = 1; i <= N; i++) {
					int cnt = Math.max(max[i - 1][j - 1], max[i - 1][j]);
					max[i][j] = cnt + alphaCnt.get(empArr[j][1])[i];
				}
				ANSWER = Math.max(ANSWER, max[N][j]);
			}
		}
		
		for (int i = 1; i <= K; i++) {
			
			if(empArr[i][2] == 0) {
				empArr[empNum][1] = empArr[i][0];
				empArr[i][2] = 1;
				solve(empNum + 1);
				empArr[i][2] = 0;
			}
			
		}
		
	}
}
