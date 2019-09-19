package graph.solution;

/*
4
6 8
1 2 8
1 3 3
6 3 3
2 5 2
2 6 9
5 3 6
1 6 7
4 5 7
2 4
6 8
2 1 6
2 4 18
5 3 4
2 6 4
6 1 20
2 3 11
3 1 19
4 3 2
2 5
7 9
2 1 22
3 7 27
1 3 8
3 5 16
2 6 22
2 5 23
4 1 5
1 7 6
5 7 29
4 6
10 14
7 1 26
8 10 47
6 5 42
1 2 2
1 5 11
7 6 46
9 7 44
2 3 30
2 8 48
3 7 38
4 5 24
7 5 1
7 8 35
5 10 22
7 9
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution0919 {

    static int N, M;
    static int a, b;
    static long s;
    static int S, E;
    static long ANS;

    static int[] parent;
    static Edge[] edges;

    static class Edge {
        int x, y;
        long cost;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T ; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            edges = new Edge[M];
            // TODO ANS는 최대값으로 변경해야하나?
            ANS = 0;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());

                Edge e = new Edge();
                e.x = a;
                e.y = b;
                e.cost = s;
                edges[i] = e;
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            Arrays.sort(edges, new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return (int) (o1.cost - o2.cost);
                }
            });

            int startIdx = 0;
            long min = 0;
            long max = 0;

            for (int i = 0; i < edges.length; i++) {
                Edge edge = edges[i];
                if(edge.x == S || edge.y == S || edge.x == E || edge.y == E){
                    // TODO max 값 다시세팅 필요?
                    startIdx = i;
                    min = edge.cost;
                    max = Long.MIN_VALUE;
                    break;
                }
            }

            for (int i = startIdx; i < M; i++) {
                int a = findRoot(edges[i].x);
                int b = findRoot(edges[i].y);
                if (isEnd()) {
                    break;
                }
                if (a == b) {
                    continue;
                } else {
                    parent[a] = b;
                    max = edges[i].cost;
                }
            }
            ANS = max - min;
            System.out.println("#" + test_case + " " + ANS);
        }

    }

    static boolean isEnd() {
        int x = findRoot(S);
        int y = findRoot(E);
        return x == y;
    }

    private static int findRoot(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            parent[x] = findRoot(parent[x]);
            return parent[x];
        }
    }

}
