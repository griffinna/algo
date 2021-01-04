package tree;

import java.io.*;
import java.util.*;

public class LowestCommonAncestor {	// https://exponential-e.tistory.com/34
	
	private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static boolean[] visit;
    private static int N;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		parent = new int[N][21];		// N : 노드갯수, 2^i 번째 부모 저장
		deep = new int[N];
		visit = new boolean[N];
		
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			tree[node1].add(node2);
			tree[node2].add(node1);
		}
		dfs(0, 0);
		connect();
		
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			sb.append(LCA(node1, node2) + "\n");
		}
		System.out.println(sb.toString());
	}
	
	// 1. 각 노드마다 바로 상위 노드에 대한 번호를 저장
	// parent[1][0] = 0 ~ parent[14][0] = 8
	static void dfs(int current, int depth) {
		visit[current] = true;
		deep[current] = depth;
		
		for (int next : tree[current]) {
			if(visit[next]) continue;
			parent[next][0] = current;
			dfs(next, depth + 1);
		}
	}
	
	// 2. 각 노드끼리 연결
	static void connect() {
		for (int i = 1; i < 21; i++) {
			for (int j = 0; j < N; j++) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];
			}
		}
	}
	
	// 3. LCA : 두 노드의 깊이를 결정
	static int LCA(int node1, int node2) { 
		// node2 가 더 깊은 노드 > node2 를 node2 까지 높이를 맞춰줌
		if(deep[node1] > deep[node2]) {
			int tmp = node1;
			node1 = node2;
			node2 = tmp;
		}
		
		for (int i = 20; i >= 0; i--) {
			int jump = 1 << i;
			if(deep[node2] - deep[node1] >= jump) node2 = parent[node2][i];
		}
		
		if(node1 == node2) return node1;
		
		for (int i = 20; i >= 0; i--) {
			if(parent[node1][i] == parent[node2][i]) continue;
			node1 = parent[node1][i];
			node2 = parent[node2][i];
		}
		return parent[node1][0];
	}
	
}
