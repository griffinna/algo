
package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://m.blog.naver.com/ndb796/221237111220
 * */
public class NetworkFlow {

	static final int MAX = 100;
	static final int INF = 1000000000;
	
	static int N;
	static int result;
	static int[][] c;		// capacity
	static int[][] f;		// flow
	static int[] d;			// ? 
	
	static LinkedList<Integer>[] a;
	
	public static void main(String[] args) {
		N = 6;
		c = new int[N + 1][N + 1];
		f = new int[N + 1][N + 1];
		d = new int[N + 1];
		a = new LinkedList[N + 1];
		
		for (int i = 1; i < a.length; i++) {
			a[i] = new LinkedList<Integer>();
		}
		
		a[1].addLast(2);
		a[2].addLast(1);	// 음의 유량도 확인하기 위함
		c[1][2] = 12;
		
		a[1].addLast(4);
		a[4].addLast(1);
		c[1][4] = 11;
		
		a[2].addLast(3);
		a[3].addLast(2);
		c[2][3] = 6;
		
		a[2].addLast(4);
		a[4].addLast(2);
		c[2][4] = 3;
		
		a[2].addLast(5);
		a[5].addLast(2);
		c[2][5] = 5;
		
		a[2].addLast(6);
		a[6].addLast(2);
		c[2][6] = 9;
		
		a[3].addLast(6);
		a[6].addLast(3);
		c[3][6] = 8;
		
		a[4].addLast(5);
		a[5].addLast(4);
		c[4][5] = 9;
		
		a[5].addLast(3);
		a[3].addLast(5);
		c[5][3] = 3;
		
		a[5].addLast(6);
		a[6].addLast(5);
		c[5][6] = 4;
		
		maxFlow(1, 6);
		
		System.out.println("result : " + result);
		
	}

	private static void maxFlow(int start, int end) {
		
		while (true) {
			Arrays.fill(d, -1);	// 모든 노드를 방문하지 않았으므로 -1으로 초기
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(start);			// 시작점을 큐에 넣어
			// BFS
			while(!queue.isEmpty()) {
				int x = queue.poll();	// 큐에서 하나를 꺼내
				for (int i = 0; i < a[x].size(); i++) {	// 인접노드 확인
					int y = a[x].get(i);				// 인접노드 정보
					// 방문하지 않은 노드 중에 용량이 남은 경우
					if(c[x][y] - f[x][y] > 0 && d[y] == -1) {
						queue.add(y);
						d[y] = x;	// 경로를 기억
						if(y == end) break;	// 도착지에 도달한 경우
					}
				}
			}
			
			if(d[end] == -1) break; // 모든 경로를 찾은 후 탈출(BFS 수행 후, 도착지에 도달하지 못하면 모든 경로를 이미 찾은 것이기 때문임)
			int flow = INF;			// 최소값을 찾기위해 inf 로 초기화
			// end ~ start 최소유량 탐색 (한번반복마다 자기 이전 경로로 돌아옴)
			for (int i = end; i != start; i = d[i]) {	// 거꾸로 최소 유량 탐색
				flow = Math.min(flow, c[d[i]][i] - f[d[i]][i]);
			}
			// 최소 유량만큼 추가
			for (int i = end; i != start; i = d[i]) {
				f[d[i]][i] += flow;
				f[i][d[i]] -= flow;	// 음의유량 처리
			}
			result += flow;
		}
	}
	
}
