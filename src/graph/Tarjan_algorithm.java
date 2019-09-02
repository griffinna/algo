package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://m.blog.naver.com/ndb796/221236952158
 */
public class Tarjan_algorithm {

	static final int MAX = 10001;
	static int id;
	static int[] d = new int[MAX];
	static boolean[] finish = new boolean[MAX];
	static LinkedList<Integer>[] a = new LinkedList[MAX];
	static LinkedList<LinkedList<Integer>> SCC = new LinkedList<LinkedList<Integer>>();
	static Stack<Integer> s = new Stack<Integer>();
	
	public static void main(String[] args) {
		
		Arrays.fill(finish, false);
		id = 0;
		
		int v = 11;
		
		for (int i = 1; i <= v; i++) {
			a[i] = new LinkedList<Integer>();
		}
		
		a[1].addLast(2);
		a[2].addLast(3);
		a[3].addLast(1);
		a[4].addLast(2);
		a[4].addLast(5);
		a[5].addLast(7);
		a[6].addLast(5);
		a[7].addLast(6);
		a[8].addLast(5);
		a[8].addLast(9);
		a[9].addLast(10);
		a[10].addLast(11);
		a[11].addLast(3);
		a[11].addLast(8);
		
		for (int i = 1; i <= v; i++) {
			if(d[i] == 0) dfs(i);
		}
		
		System.out.printf("SCC 의 갯수 : %d\n", SCC.size());
		for (int i = 0; i < SCC.size(); i++) {
			System.out.printf("%d 번째 SCC : ", i + 1);
			for (int j = 0; j < SCC.get(i).size(); j++) {
				System.out.printf("%d ", SCC.get(i).get(j));
			}
			System.out.println();
		}
		
	}
	
	// DFS는 총 정점의 갯수만큼 실행
	static int dfs(int x) {
		d[x] = ++id;	// 노드마다 고유한 번호를 할당
		s.push(x);		// 스택에 자기 자신을 삽입
		
		int parent = d[x];
		for (int i = 0; i < a[x].size(); i++) {
			int y = a[x].get(i);
			if(d[y] == 0) {
				parent = Math.min(parent, dfs(y));	// 방문하지 않은 이웃
			}else if(!finish[y]) {
				parent = Math.min(parent, d[y]);	// 처리중인 이웃
			}
		}
		
		// 부모노드가 자기 자신인 경우
		if(parent == d[x]) {
			LinkedList<Integer> scc = new LinkedList<Integer>();
			while(true) {
				int t = s.pop();
				scc.addLast(t);
				finish[t] = true;
				if(t == x) break;
			}
			SCC.addLast(scc);
		}
		
		return parent;
	}
	
}
