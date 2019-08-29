package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Dijkstra's algorithm : to find shortest path from one node("s") to all other nodes.
/*
 5 10
 1 2 3
 1 3 6
 1 4 8
 1 5 7
 2 3 2
 2 4 4
 2 5 8
 3 4 5
 3 5 5
 4 5 2
 */
public class DijkstraPriorityQueue {

	static int[][] graph;	// graph
	static int[] dist;	// shortest distance from "s"
//	static boolean[] visited;	// all false init
	static int N, E;
	static PriorityQueue<NodeInfo> queue;
	
	static class NodeInfo implements Comparable<NodeInfo>{
		
		int idx, dist;

		public NodeInfo(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(NodeInfo o) {
			return dist <= o.dist ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
//		graph = new int[][]{{ 0, 0, 0, 0, 0, 0 },
//					        { 0, 0, 3, 6, 8, 7 },
//						    { 0, 3, 0, 2, 4, 8 },
//						    { 0, 6, 2, 0, 5, 5 },
//						    { 0, 8, 4, 5, 0, 2 },
//						    { 0, 7, 8, 5, 2, 0 }};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[N + 1];
//		visited = new boolean[N + 1];
		graph = new int[N + 1][N + 1];
		
		queue = new PriorityQueue<NodeInfo>();
		
		Arrays.fill(dist, Integer.MAX_VALUE);
//		Arrays.fill(visited, false);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[x][y] = v;
			graph[y][x] = v;
			
		}
		
		dijkstra(1);		// start node
		
	}
	
	/**
	 * 
	 * @param s : start node index
	 */
	static void dijkstra(int s) {
	
		dist[s] = 0;
		queue.offer(new NodeInfo(s, dist[s]));
		
		while(!queue.isEmpty()) {
			
			NodeInfo nInfo = queue.poll();
			
			int cost = nInfo.dist;
			int idx = nInfo.idx;
			
			if(cost > dist[idx]) {
				continue;
			}
			
			System.out.print(" - " + idx);
			
			for (int i = 0; i <= N; i++) {
				if(graph[idx][i] != 0 && dist[i] > dist[idx] + graph[idx][i]) {
					dist[i] = dist[idx] + graph[idx][i];
					queue.offer(new NodeInfo(i, dist[i]));
				}
			}
		}
		
		System.out.println();
		for(int i = 1; i <= N; i++) {
			System.out.print(dist[i] + " ");
		}
	
	}
}
