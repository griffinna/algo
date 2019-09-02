package graph;

public class FloydWarshall_basic {

	static int number = 4;
	static int INF = 10000000;
	
	static int[][] graph;
	static int[][] result;
	
	public static void main(String[] args) {
		
		graph = new int[][]{
			{0, 5, INF, 8},
			{7, 0, 9, INF},
			{2, INF, 0, 4},
			{INF, INF, 3, 0}
		};
		
		result = new int[number][number];
		
		floydWarshall();
		
	}
	
	static void floydWarshall() {
		
		// 결과그래프 초기화
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				result[i][j] = graph[i][j];
			}
		}
		
		// k : 거쳐가는 노드
		for(int k = 0; k < number; k++) {
			// i : 출발 노드
			for(int i = 0; i < number; i++) {
				// j : 도착 노드
				for(int j = 0; j < number; j++) {
					if(result[i][k] + result[k][j] < result[i][j]) {
						result[i][j] = result[i][k] + result[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
}
