package acmicpc.TopologySort;

import java.io.*;
import java.util.*;

public class Solution_2529 {
    static ArrayList<Integer>[] adj;
    static int maxNum;
    static int minNum = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // {1} > {2} : 2 -> 1
        // {1} < {2} : 1 -> 2
        int[] indegree = new int[N + 2];
        adj = new ArrayList[N + 2];
        int[] min = new int[N + 2];
        int[] max = new int[N + 2];
        maxNum = 9 - N;
        Arrays.fill(min, -1);
        Arrays.fill(max, -1);
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        int a, b;
        String cmd;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cmd = st.nextToken();
            if (cmd.equals("<")) {
                a = i;
                b = i + 1;
            }else{
                a = i + 1;
                b = i;
            }
            if(adj[a] == null) adj[a] = new ArrayList<>();
            adj[a].add(b);
            indegree[b]++;
        }
        int[] indegreeTmp = indegree.clone();
        for (int i = 1; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                minQ.add(i);
                maxQ.add(i);
            }
        }
        while (!minQ.isEmpty()) {
            int now = minQ.poll();
            min[now] = minNum;
            minNum++;
            if (adj[now] != null) {
                for (int next: adj[now]) {
                    if(min[next] == -1) {
                        indegree[next]--;
                        if(indegree[next] == 0) minQ.add(next);
                    }
                }
            }
        }

        while (!maxQ.isEmpty()) {
            int now = maxQ.poll();
            max[now] = maxNum;
            maxNum++;
            if (adj[now] != null) {
                for (int next: adj[now]) {
                    if(max[next] == -1) {
                        indegreeTmp[next]--;
                        if(indegreeTmp[next] == 0) maxQ.add(next);
                    }
                }
            }
        }
        StringBuffer sbMin = new StringBuffer();
        StringBuffer sbMax = new StringBuffer();
        for (int i = 1; i <= N + 1; i++) {
            sbMin.append(min[i]);
            sbMax.append(max[i]);
        }
        System.out.println(sbMax.toString());
        System.out.println(sbMin.toString());
    }
}
