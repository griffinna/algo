package acmicpc.FailSolution;

import java.io.*;
import java.util.*;

public class Solution_16562 {
    static int[] parent, cost;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        cost = new int[N + 1];
        parent = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            int tempParent = findRoot(i);
            if(tempParent != 0) {
                sum += cost[tempParent];
                union(0, tempParent);
            }
        }

        if (sum > K) {
            System.out.println("Oh no");
        }else{
            System.out.println(sum);
        }

    }

    private static void union(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);
        if(a == b) return;
        if (cost[a] <= cost[b]) {
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    private static int findRoot(int a) {
        if(a == parent[a]) return a;
        return parent[a] = findRoot(parent[a]);
    }

}
