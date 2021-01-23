package acmicpc.NetworkFolw;

import java.io.*;
import java.util.*;
public class Solution_6068 {
    static int N, MAX;
    static int[][] c;   // capability
    static int[][] f;   // flow
    static int[] d;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MAX = 52;
        c = new int[MAX][MAX];
        f = new int[MAX][MAX];
        adj = new ArrayList[MAX];
        d = new int[MAX];
        StringTokenizer st;
        int s, e, w;
        char str, end;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            str = st.nextToken().toCharArray()[0];
            end = st.nextToken().toCharArray()[0];
            if(str > 'Z'){
                s = str - 'a' + 26;
            }else {
                s = str - 'A';
            }
            if (end > 'Z') {
                e = end - 'a' + 26;
            } else {
                e = end - 'A';
            }

            w = Integer.parseInt(st.nextToken());
            if(adj[s] == null) adj[s] = new ArrayList<>();
            if(adj[e] == null) adj[e] = new ArrayList<>();
            adj[s].add(e);
            adj[e].add(s);

            c[s][e] += w;
            c[e][s] += w;
        }

        int totalFlow = 0;
        int S = 0;
        int T = 25;

        while (true) {
            Arrays.fill(d, -1);
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.push(S);

            while (!q.isEmpty()) {
                int curr = q.poll();
                if(adj[curr] != null){
                    for (int i = 0; i < adj[curr].size(); i++) {
                        int next = adj[curr].get(i);
                        if (d[next] == -1) {
                            if (c[curr][next] - f[curr][next] > 0) {
                                q.push(next);
                                d[next] = curr;
                                if (next == T) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (d[T] == -1) {
                break;
            }

            int flow = 987654321; // INF
            for (int i = T; i != S; i = d[i]) {
                int next = d[i];
                flow = Math.min(flow, c[next][i] - f[next][i]);
            }

            for (int i = T; i != S; i = d[i]) {
                int next = d[i];
                f[next][i] += flow;
                f[i][next] -= flow;
            }
            totalFlow += flow;
        }
        System.out.println(totalFlow);
    }
}
