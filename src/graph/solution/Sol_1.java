package graph.solution;

import java.io.*;
import java.util.*;

public class Sol_1 {
    static int[] parent;
    static int SNODE =0 ;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int testcase = 1; testcase <=T; testcase++) {
            st = new StringTokenizer(br.readLine());
            int node_cnt = Integer.parseInt(st.nextToken());
            int road_cnt = Integer.parseInt(st.nextToken());
            int[][] road = new int[road_cnt][3];
            for (int i = 0; i < road_cnt; i++) {
                st = new StringTokenizer(br.readLine());
                road[i][0] = Integer.parseInt(st.nextToken());
                road[i][1] = Integer.parseInt(st.nextToken());
                road[i][2] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(road, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2]-o2[2];
                }
            });
            int ENODE = node_cnt-1;
            int answer=1_000;
            int[] init_parent = new int[node_cnt];
            for (int k = 0; k < node_cnt; k++) {
                init_parent[k] = k;
            }
            int pre_speed = -1;
            for (int i = 0; i < road_cnt; i++) {
                if(pre_speed == road[i][2]) continue;
                boolean chk = false;
                parent= Arrays.copyOf(init_parent, node_cnt);
                for (int j = i; j < road_cnt; j++) {
                    int a = find(road[j][0]);
                    int b = find(road[j][1]);
                    if(a==b) continue;
                    parent[b]=a;
                    if(find(SNODE) == find(ENODE)){
                        answer=Math.min(answer, road[j][2]-road[i][2]);
                        parent= Arrays.copyOf(init_parent, node_cnt);
                        for (int k = j; k >=i; k--) {
                            int x = find(road[k][0]);
                            int y = find(road[k][1]);
                            if(x==y) continue;
                            parent[y]=x;
                            if(find(SNODE) == find(ENODE)){
                                answer=Math.min(answer, road[j][2]-road[k][2]);
                                i=k;
                                break;
                            }
                        }
                        chk=true;
                        break;
                    }
                }
                if(chk==false || answer==0) break;
                pre_speed = road[i][2];
            }
            System.out.println(answer);
        }
    }
    static int find(int i) {
        if(parent[i] != i) parent[i] = find(parent[i]);
        return parent[i];
    }
}