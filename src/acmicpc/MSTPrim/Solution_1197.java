package acmicpc.MSTPrim;

import java.io.*;
import java.util.*;

public class Solution_1197 {	// Prim Algorithm

	static class Node {
		int next;
		int weight;
	}
	
	static boolean[] visit;
	static int result;
	static ArrayList<Node>[] list;
	
	static class NodeCompare implements Comparator<Node>{
		@Override
		public int compare(Node o1, Node o2) {
			return o1.weight - o2.weight;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());		// 정점
		int E = Integer.parseInt(st.nextToken());		// 간선
		
		list = new ArrayList[V + 1];		
		visit = new boolean[V + 1];
		
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		int s, e, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			Node node = new Node();
			node.next = s;
			node.weight = w;
			list[e].add(node);
			Node node2 = new Node();
			node2.next = e;
			node2.weight = w;
			list[s].add(node2);
		}
		
		// 최소값을 빼주기위한 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new NodeCompare());
		visit[1] = true;
		pq.addAll(list[1]);
		int cnt = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int next = node.next;
			if(!visit[next]) {
				int weight = node.weight;
				result += weight;
				visit[next] = true;
				cnt++;
				if(cnt > V) break;
				pq.addAll(list[next]);
			}
		}
		System.out.println(result);
	}
	
}
