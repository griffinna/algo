package graph.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Sol_2 {
    private static int m = 0;
    private static int n = 0;
    private static Vertex[] vertices = null;
    private static int max;
    private static int min;
    private static int result;
    private static Edge[] edges;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(in.readLine());
        while(testCase --> 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            vertices = new Vertex[n];
            for(int i = 0; i < n; i++) vertices[i] = new Vertex(i);
            edges = new Edge[m];
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(from, to, cost);
            }
            result = Integer.MAX_VALUE;
            Arrays.sort(edges);
            for(int i = 0; i < n; i++) {
                kruskal(i);
                if(result == 0) break;
            }
            System.out.println(result);
        }
    }

    private static void kruskal(int s) {
        init();
        int edgeCount = edges.length;
        for(int i = s; i < edgeCount; i++) {
            Edge edge = edges[i];
            int fromParent = find(edge.from);
            int toParent = find(edge.to);
            if(fromParent == toParent) continue;
            vertices[toParent].parent = fromParent;
            int cost = edge.cost;
            if(max < cost) max = cost;
            if(min > cost) min = cost;
            if(min != Integer.MAX_VALUE && result < max - min) break;
            if(find(0) == find(n - 1)) {
                result = max - min;
                break;
            }
        }
    }

    private static int find(int num) {
        if(vertices[num].parent == num) return num;
        return vertices[num].parent = find(vertices[num].parent);
    }

    private static void init() {
        max = 0;
        min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) vertices[i].parent = i;
    }
}

class Vertex {
    public int parent;
    public Vertex(int parent) {
        this.parent = parent;
    }
}

class Edge implements Comparable<Edge> {
    public int from;
    public int to;
    public int cost;
    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) {
        return this.cost > o.cost ? 1 : -1;
    }
}