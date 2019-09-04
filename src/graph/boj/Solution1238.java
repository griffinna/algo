package graph.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
문제
N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.

어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 
이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 
하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. 
N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

입력
첫째 줄에 N(1 <= N <= 1,000), M(1 <= M <= 10,000), X가 공백으로 구분되어 입력된다. 
두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다.

모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.

출력
첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.

[IN]
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3

[OUT]
10

*/
public class Solution1238 {
	
	static int N;	// 학생&마을 수
	static int M;	// 도로 수
	static int X;	// 파티 마을
	static int MAX;	// 가장 오래걸리는 소요시간
	static int[][] map;
	static int[][] map2;
	static int[] min;
	static int[] result;
	
	static PriorityQueue<Node> queue;
	static int x, y, t;
	
	static class Node implements Comparable<Node>{
		int idx, time;
		public Node(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return time <= o.time ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		MAX = 0;
		map = new int[N + 1][N + 1];
		map2 = new int[N + 1][N + 1];
		result = new int[N + 1];
		
		queue = new PriorityQueue<Node>();
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			map[x][y] = t;
			map2[y][x] = t;
		}
		
		dijkstra(X, map);
		dijkstra(X, map2);
		
		for (int i = 1; i < result.length; i++) {
			MAX = (MAX < result[i]) ? result[i] : MAX;
		}
		System.out.println(MAX);
	}

	private static void dijkstra(int x, int[][] graph) {

		min = new int[N + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		
		min[x] = 0;
		queue.offer(new Node(x, min[x]));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int idx = node.idx;
			int time = node.time;
			if(time > min[idx]) {
				continue;
			}
			for (int i = 1; i <= N; i++) {
				if(graph[idx][i] != 0 && min[i] > graph[idx][i] + min[idx]) {
					min[i] = graph[idx][i] + min[idx];
					queue.offer(new Node(i, min[i]));
				}
			}
		}
		
		for (int i = 1; i < min.length; i++) {
			result[i] += min[i];
		}
		
	}
	
}
