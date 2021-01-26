package acmicpc.ArticulationPoint;

import java.io.*;
import java.util.*;
/**
 * 단절점
 * 조건 1. 정점 A가 루트 && 자식이 2개 이상이면 단절점이다.
 * 조건 2. 정점 A의 자식노드들이 A를 거치지않고 A보다 빠른 방문번호를 가진다면 단절점이다.
 *         (정점 A에서 자식노드들이 A를 거치지않고 정점 A 보다 빠른 방문번호를 가진 정점으로 갈 수 없다.)
 * */
public class Solution_11266 {

    static ArrayList<Integer>[] adj;    // 인접한 정점 정보
    static int[] index;                 // 방문순번 정보
    static boolean[] isJoint;           // 단절점여부
    static int cnt;                     // 방문번호 (1부터 시작)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        adj = new ArrayList[V + 1];
        index = new int[V + 1];
        isJoint = new boolean[V + 1];
        int a, b;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(adj[a] == null) adj[a] = new ArrayList<>();
            if(adj[b] == null) adj[b] = new ArrayList<>();
            adj[a].add(b);
            adj[b].add(a);
        }
        cnt = 1;
        for (int i = 1; i <= V; i++) {
            // 아직 방문하지 않은 정점을 루트로 가정하고 dfs 수행
            if(index[i] == 0) dfs(i, true);
        }
        StringBuffer sb = new StringBuffer();
        int jointCnt = 0;
        for (int i = 1; i < isJoint.length; i++) {
            if(isJoint[i]) {
                sb.append(i + " ");
                jointCnt++;
            }
        }
        System.out.println(jointCnt);
        System.out.println(sb.toString());
    }

    private static int dfs(int idx, boolean isRoot) {
        index[idx] = cnt++;         // idx 번째 정점의 방문순번을 저장하고 cnt 를 1 증가시킨다
        int min = index[idx];       // 현재 정점의 방문순번 최소 (return 할 값)
        int child = 0;              // 자식노드 카운트 할 변수
        if (adj[idx] != null) {     // 인접한 정점이 있다면
            for (int next: adj[idx]) {      // 인접한 정점들 중에서
                if (index[next] == 0) {     // next 정점을 아직 방문하지 않았으면
                    child++;                // idx 번째 정점의 자식이다.
                    int low = dfs(next, false);     // next 정점을 dfs 탐색한 후 방문순번 최소값을 찾는다.
                    min = Math.min(min, low);               // idx 번째 정점의 방문순번 최소값과 비교한다.
                    // idx 정점이 루트정점이고, next 정점의 방문순번최소값이 더 작으면 idx 정점은 단절점이다.
                    if (!isRoot && low >= index[idx]) isJoint[idx] = true;
                } else {                // next 정점을 이미 방문했으면
                    min = Math.min(min, index[next]);       // idx 번째 정점의 방문순번 최소값과 next 번째 정점의 방문순번을 비교한다.
                }
            }
        }
        // 정점 루트이고, 자식이 2개 이상이라면 단절점이다.
        if (isRoot && child >= 2) {
            isJoint[idx] = true;
        }
        return min;
    }
}
