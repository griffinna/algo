package graph.koitp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
N개의 도시가 있다. 각 도시는 1번부터 N번까지 번호가 매겨져있다. 그리고 서로 다른 두 도시를 잇는 
M개의 도로가 있다. 도로마다 길이가 다를 수 있으며, 어떠한 두 도시는 여러 개의 도로로 이어져 있을 수 있다.

태양이의 집은 1번 도시에 있고, 명우의 집은 N번 도시에 있다. 
태양이는 자신의 집에서 명우의 집으로 놀러가려고 한다. 
단, 조금이라도 빨리 명우를 보고 싶어하는 마음에 최대한 빠르게 이동하려고 한다.

태양이를 도와 1번 도시에서 N번 도시로 가는 최단 거리를 구하는 프로그램을 작성하시오.

첫줄에 테스트케이스 T 입력
이후, 도시의 수를 나타내는 자연수 N과 도로의 개수를 나타내는 정수 M이 공백으로 구분되어 주어진다. (1≤N≤100,000,0≤M≤300,000)
그 다음 M개의 줄에 각 도로를 나타내는 세 개의 자연수 a, b, c가 공백으로 구분되어 주어진다. 
이는 a번 도시와 b번 도시를 연결하는 길이 c인 도로를 의미한다. (1≤a,b≤N,a≠b,1≤c≤106)

1 번 도시에서 N번 도시로 주어진 도로를 통해 이동할 때 최단 거리를 출력한다. 
만약 이동할 수 없으면 −1을 출력한다.

 */
public class ShortestPath {

	static int N, M;		// N : 도시수 , M : 도로
	static int a, b, c;
	static long[] distance;
	static boolean[] checked;
	static Object[] adjList;	// 인접리스트
	
	static PriorityQueue<Dist> queue;
	
	static class Dist implements Comparable<Dist> {
		int idx;
		long dist;
		@Override
		public int compareTo(Dist o) {
//			return dist <= o.dist ? -1 : 1;
			return (int) (this.dist - o.dist);
		}
	}
	static class City { int str, end, time; }
	
	public static void main(String[] args) throws Exception {
		
		String path = ShortestPath.class.getResource("").getPath();
		System.setIn(new FileInputStream( path + "ShortestPath_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			queue = new PriorityQueue<Dist>();
			adjList = new Object[N + 1];	// idx 는 1번부터 시작
			distance = new long[N + 1];
			checked = new boolean[N + 1];
			Arrays.fill(distance, -1);
			distance[1] = 0;				// 태양이 집은 1번도시
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				City city = new City();
				city.str = a;
				city.end = b;
				city.time = c;
				
				ArrayList<City> list;
				if(adjList[a] != null) {
					list = (ArrayList<City>) adjList[a];
				}else {
					list = new ArrayList<City>();
				}
				list.add(city);
				adjList[a] = list;
				
				City c2 = new City();
	            c2.str = b;
	            c2.end = a;
	            c2.time = c;
	 
	            ArrayList<City> list2;
	            if(adjList[b] != null){
	                list2 = (ArrayList<City>) adjList[b];
	            }else{
	                list2 = new ArrayList();
	            }
	            list2.add(c2);
	            adjList[b] = list2;
	            
			}
			
			Dist init = new Dist();
			init.idx = 1;
			init.dist = 0;
			
			queue.add(init);
			
			while(!queue.isEmpty()) {
				Dist d = queue.poll();
				if(adjList[d.idx] != null && !checked[d.idx]) {
					checked[d.idx] = true;
					ArrayList<City> list = (ArrayList<City>) adjList[d.idx];
					for(City city : list) {
						if(!checked[city.end]) {
							long fromStart = distance[city.str] + city.time;
							if(distance[city.end] < 0 || fromStart < distance[city.end]) {
								distance[city.end] = fromStart;
								Dist updateDist = new Dist();
								updateDist.idx = city.end;
								updateDist.dist = fromStart;
								queue.add(updateDist);
							}
						}
					}
				}
			}
			
			System.out.println("#" + test_case + " " + distance[N]);
			
		}
		
		
	}
}
