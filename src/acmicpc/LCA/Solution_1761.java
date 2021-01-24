package acmicpc.LCA;

import java.io.*;
import java.util.*;
/**
 * 최소공통조상 (LCA: Lowest Common Ancestor)
 *
 * 1. 모든 노드에 대해 depth (깊이) 를 구한다 > dfs
 * 2. 모든 노드에 대해 2^i번째 부모노드를 구한다.
 * 3. 최소공통조상을 찾을 두 노드를 찾는다.
 * 4. 두 노드의 깊이 (depth) 가 동일하게 거슬러 올라간다.
 * 5. 최상단 노드부터 내려오는 방식으로 두 노드의 공통부모를 찾아낸다.
 *
 * */
public class Solution_1761 {
    static int[][] parent;
    static int[] d, fromRoot;
    static boolean[] visit;
    static ArrayList<Node>[] adj;
    static class Node {
        int idx, dist;
        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
    static int N, MD;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int a, b, c;
        MD = 0;
        while(Math.pow(2, MD) < N) {
            MD++;
        }
        parent = new int[N + 1][MD];
        d = new int[N + 1];
        fromRoot = new int[N + 1];
        visit = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            parent[b][0] = a;

            if(adj[a] == null) adj[a] = new ArrayList<>();
            if(adj[b] == null) adj[b] = new ArrayList<>();
            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        // 1. 모든 노드에 대해 깊이를 구한다.
        bfs(1, 0);
        setParent();
        int M = Integer.parseInt(br.readLine());
        int lca;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lca = LCA(a, b);
            System.out.println(fromRoot[a] + fromRoot[b] - (fromRoot[lca] * 2));
        }
    }

    // 1. 모든 노드에 대해 depth (깊이)를 구하기 위한 dfs
    private static void bfs(int x, int depth) {
        visit[x] = true;    // 방문한 노드 체크
        d[x] = depth;       // 방문한 노드의 depth 를 저장
        if (adj[x] != null) {
            for (Node next: adj[x]) {               // 노드와 연결된 다음 노드 중에
                if(visit[next.idx]) continue;       // 아직 방문하지 않은 노드를 찾아서
                fromRoot[next.idx] = fromRoot[x] + next.dist;
                parent[next.idx][0] = x;                            // 해당 노드의 2^0 번째 (바로 위) 부모를 본인으로 세팅
                bfs(next.idx, depth + 1);   // 깊이를 1 더해서 다음 bfs 시작
            }
        }
    }

    // 2. 모든 노드에 대해 2^i 번째 부모를 구한다.
    private static void setParent() {
        for (int j = 1; j < MD; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }
    }

    private static int LCA(int x, int y) {  // 3. 최소공통조상을 찾을 두 노드를 설정
        if (d[x] > d[y]) {      //  y 가 더 깊도록 설정한다. (기준)
            int tmp = x;
            x = y;
            y = tmp;
        }

        // 4. 두 노드의 깊이 (depth) 가 동일하게 거슬러 올라간다.
        // d[y] 를 d[x] 에 동일하게 맞춤
        for (int i = MD - 1; i >= 0; i--) {
            if (d[y] - d[x] >= (1 << i)) {
                y = parent[y][i];
            }
        }

        // 부모가 같으면 리턴
        if(x == y) return x;

        // 5. 최상단 노드부터 내려오는 방식으로 두 노드의 공통 부모를 찾아낸다.
        for (int i = MD - 1; i >= 0; i--) {
            if (parent[x][i] != parent[y][i]) { // 루트부터 둘의 부모가 다를 때까지 내려간다.
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        // 바로 위 (2^0) 부모가 최소공통부모
        return parent[y][0];
    }

}
