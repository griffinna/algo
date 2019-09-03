package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * 최단경로 
 * (https://www.acmicpc.net/problem/1753)
 * 
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 
 * 단, 모든 간선의 가중치는 10 이하의 자연수이다.
 * 
 * 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1≤V≤20,000, 1≤E≤300,000) 
 * 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 
 * 둘째 줄에는 시작 정점의 번호 K(1≤K≤V)가 주어진다. 셋
 * 째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 
 * 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. 
 * u와 v는 서로 다르며 w는 10 이하의 자연수이다. 
 * 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
 * 
 * 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 
 * 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
 * 
 * input
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6

output
0
2
3
7
INF

 * */
public class Solution1753 {

	static int V;	// 정점의 개수
	static int E;	// 간선의 개수
	static int K;	// 시작 정점번호
	
	static int u, v, w;	// u 에서 v 로 가는 가중치 w
	
	static int[][] graph;
	static PriorityQueue<NodeInfo> queue;
	static int[] d;
	
	static class NodeInfo implements Comparable<NodeInfo>{
		int idx, dist;

		public NodeInfo(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(NodeInfo o) {
			return dist <= o.dist ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		// init
		graph = new int[V + 1][V + 1];
		queue = new PriorityQueue<NodeInfo>();
		d = new int[V + 1];
		Arrays.fill(d, Integer.MAX_VALUE);
		
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			graph[u][v] = w;
			
		}

		dijkstra(K);
		
		String result = "";
		for (int i = 1; i < d.length; i++) {
			if(d[i] == Integer.MAX_VALUE) {
				result = "INF";
			}else {
				result = String.valueOf(d[i]);
			}
			System.out.println(result);
		}
	}

	private static void dijkstra(int k) {
		
		d[k] = 0;
		queue.offer(new NodeInfo(k, d[k]));
		while(!queue.isEmpty()) {
			NodeInfo nInfo = queue.poll();
			int dist = nInfo.dist;
			int idx = nInfo.idx;
			if(dist > d[idx]) {
				continue;
			}
			for (int i = 0; i <= V; i++) {
				if(graph[idx][i] != 0 && d[i] > d[idx] + graph[idx][i]) {
					d[i] = d[idx] + graph[idx][i];
					queue.offer(new NodeInfo(i, d[i]));
				}
			}
		}
		
	}
	
}
