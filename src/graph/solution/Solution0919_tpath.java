package graph.solution;

/*
3
2 1
0 1 100
4 3
0 1 127
1 2 14
2 3 96
4 4
0 1 100
1 3 99
0 2 17
2 3 10
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution0919_tpath {

    static int N, M;
    static int a, b, s;
//    static long s;
    static int S, E;
    static int ANS;

    static int[] parent;
    static Edge[] edges;

    static class Edge {
        int x, y, cost;
//        long cost;

        @Override
        public String toString() {
            return "Edge{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cost=" + cost +
                    '}';
        }
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
//            ANS = 0;
            ANS = Integer.MAX_VALUE;
//            for (int i = 0; i < N; i++) {
//                parent[i] = i;
//            }

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

//            st = new StringTokenizer(br.readLine());
            S = 0;
            E = N - 1;

            Arrays.sort(edges, new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return (int) (o1.cost - o2.cost);
                }
            });

            int startIdx = 0;
            int min = 0;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < edges.length; i++) {
                Edge edge = edges[i];
//                System.out.println(edge.toString() + S + " / " + E );
                if(edge.x == S || edge.y == S || edge.x == E || edge.y == E){
                    // TODO max 값 다시세팅 필요?
                    boolean flag = false;
                    startIdx = i;
//                    System.out.println("StartIdx : " + i);
                    min = edge.cost;
                    for (int k = 0; k < N; k++) {
                        parent[k] = k;
                    }

                    for (int j = startIdx; j < M; j++) {
                        int a = findRoot(edges[j].x);
                        int b = findRoot(edges[j].y);
                        if (a == b) {
                            continue;
                        } else {
                            parent[a] = b;
                            max = edges[j].cost;
                        }
                        if (isEnd()) {
                            int diff = max - min;
                            ANS = Math.min(ANS, diff);

//                            debugging();
//                            System.out.print("-> " + ANS);
//                            System.out.println();
                            flag = true;
                            break;
                        }
                    }
//                    System.out.println("0--000000---- " + flag);
                    if (!flag) {
                        for (int j = startIdx - 1; j >=0; j--) {
                            int a = findRoot(edges[j].x);
                            int b = findRoot(edges[j].y);
                            if (a == b) {
                                continue;
                            } else {
                                parent[a] = b;
                                min = edges[j].cost;
                            }
                            if (isEnd()) {
                                int diff = max - min;
                                ANS = Math.min(ANS, diff);

//                                debugging();
//                                System.out.print("-> " + ANS);
//                                System.out.println();

                                for (int k = M - 1; k > startIdx; k--) {
                                    Edge e = edges[k];
//                                    int x = findRoot(e.x);
//                                    int y = findRoot(e.y);

                                    parent[e.x] = e.x;

                                    if (isEnd()) {
                                        max = edges[k-1].cost;
                                        continue;
                                    }else{
                                        break;
                                    }
                                }

                                break;
                            }
                        }
                    }

                }

            }

            System.out.println(ANS);
//            System.out.println("#" + test_case + " " + ANS);
        }

    }

    private static void debugging() {
        int a = findRoot(S);
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == a) {
                System.out.print(i + " - ");
            }
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
