package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author 나가람
 *	

입력 : 정점 수 / 간선 수 / 시작노드
		간선 수 만큼 시작정점 - 종료정점 - 비용
시작노드로부터 각 정점까지 최소비용 

input
8 11 1
1 2 3
1 5 4
1 4 4
2 3 2
3 4 1
4 5 2
5 6 4
4 7 6
7 6 3
3 8 3
6 8 2 

out 
0 3 5 4 4 8 10 8
 *
 *
 */

public class DijkstraBasic {

	static int N;	// node count
	static int E;	// edge count
	static int S;	// start node
	static int x, y, val;
	static int[][] map;		// 노드간 거리
	static int[] dist;		// 해당노드까지의 최단거리
	static boolean[] visited;	// 방문여부
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		dist = new int[N + 1];
		visited = new boolean[N + 1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);		// 최단거리일때는 최대값으로 초기화
		Arrays.fill(visited, false);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			val = Integer.parseInt(st.nextToken());
			
			inputMap(x, y, val);
		}
		
		dijkstra();
		
	}
	static void dijkstra() {
		
		// init start node info
		dist[S] = 0;
		visited[S] = true;
		
		// 연결노드 distance 갱신
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && map[S][i] != 0) {
				dist[i] = map[S][i];
			}
		}
		
		for (int i = 0; i < N - 1; i++) {
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			
			// find min value
			for (int j = 1; j <= N; j++) {
				if(!visited[j] && dist[j] != Integer.MAX_VALUE) {
					if(dist[j] < min) {
						min = dist[j];
						minIdx = j;
					}
				}
			}
			
			visited[minIdx] = true;
			
			for (int j = 1; j <= N; j++) {
				if(!visited[j] && map[minIdx][j] != 0) {
					if(dist[j] > dist[minIdx] + map[minIdx][j]) {
						dist[j] = dist[minIdx] + map[minIdx][j];
					}
				}
			}
			
		}
		
		// result
		for (int i = 1; i <= N; i++) {
			System.out.print(dist[i] + " ");
		}
		
	}

	static void inputMap(int x, int y, int val) {
		map[x][y] = val;
		map[y][x] = val;
	}
	
}
