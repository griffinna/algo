package dp.engineer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Sol3 {

	static int N, M, K;
	static int ANSWER;
	static int[][] worker;
	static int[][] result;
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
			
			// setting
			worker = new int[27][N + 1];
			result = new int[N + 1][K + 1];
			alphaCnt = new HashMap<Integer, int[]>();
			empArr = new int[K + 1][3];	// 0 : alphabet, 1 : now, visit (0: false, 1 : true)
//			ANSWER = 0;
			
			// 작업자 정보
			String workerStr = "0" + br.readLine();
			char[] workerArr = workerStr.toCharArray();
			
			// 물건정보
			for (int i = 1; i <= M; i++) {
				char[] word = ("0" + br.readLine()).toCharArray();
				for (int j = 1; j <= N; j++) {
					int alphabet = ((int) word[j] - 64); 
					if(workerStr.indexOf(word[j]) > -1) {
						worker[alphabet][j]++;
					}
				}
			}
			
			// 
			for (int i = 1; i <= K; i++) {
				int target = ((int) workerArr[i] - 64);
				empArr[i][0] = target;
				alphaCnt.put(target, worker[target]);
			}
//			ANSWER = solve(1);
			System.out.println("#" + test_case + " " + solve(1));
		}
	}
	
	private static int solve(int empIdx) {
//		System.out.println(empNum);
		int sum = 0;
		if(empIdx > K) {
			for (int j = 1; j <= K; j++) {
				int[] unitCntArr = alphaCnt.get(empArr[j][1]);
				for (int i = 1; i <= N; i++) {
					int cnt = Math.max(result[i - 1][j - 1], result[i - 1][j]);
					result[i][j] = cnt + unitCntArr[i];
//					System.out.println("maxxxxxxxxxxxxx : " + max[i][j]);
				}
				sum = Math.max(result[N][j], sum);
			}
//			System.out.println("mmmm : " + ans);
			return sum;
		}
		
		for (int i = 1; i <= K; i++) {
			boolean visit = empArr[i][2] == 1 ? true : false;
			if(!visit) {
				empArr[empIdx][1] = empArr[i][0];
				empArr[i][2] = 1;
				sum = Math.max(solve(empIdx + 1), sum);
				empArr[i][2] = 0;
			}
		}
		return sum;
	}
}
