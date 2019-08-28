package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//MST(MCST, Minimum Cost Spanning Tree)
public class ExpressWay {
    static int N;
    static int M;
    static int[] parent;
    static BufferedReader br;
    static StringTokenizer st;
    static Edge[] edges;
    static int minCost = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new Edge[M];
        parent = new int[N+1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        br.close();

        //주어진 간선들을 비용에 대한 오른차순으로 정렬
        Arrays.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                return a.cost - b.cost;
            }
        });

        for (int i = 0; i < M; i++) {

            int rootX = findRoot(edges[i].x);
            int rootY = findRoot(edges[i].y);

            if(rootX == rootY){
                continue;
            }else{
                /*Union-Find의 Union
                두개 Node를 연결. 즉 X의 Root 노드를 Y의 Root노드로 변경*/
                parent[rootX] = rootY;
                minCost = minCost + edges[i].cost;
            }

        }

        System.out.println(minCost);

    }

    //Union-Find 정점 x의 대표 node 찾기
    private static int findRoot(int x){
        if(x == parent[x]) {
            return x;
        }else{
            parent[x] = findRoot(parent[x]);
            return parent[x];
        }

    }

    private static class Edge{
        int x;
        int y;
        int cost;
        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

}

