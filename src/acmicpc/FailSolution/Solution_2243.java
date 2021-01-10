package acmicpc.FailSolution;

import java.io.*;
import java.util.*;

public class Solution_2243 {static int N;   // 사탕상자에 손을 댄 횟수
    static long[] tree;
    static int size;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        size = 2;
        while (size < N) {
            size *= 2;
        }
        tree = new long[size * 2];
        int cmd, idx, cnt;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            idx = Integer.parseInt(st.nextToken());
            if (cmd == 1) { // idx 번째 캔디 찾기
                int n = getCandyIdx(1, 1, size, idx);
                System.out.println(n);
                update(1, 1, size, n, -1);
            } else {
                cnt = Integer.parseInt(st.nextToken());
                update(1, 1, size, idx, cnt);
            }
        }
    }

    static long update(int node, int start, int end, int idx, int val) {
        if(idx < start || end < idx) return tree[node];
        if (start == end) {
            return tree[node] += val;
        }

        int mid = (start + end) / 2;
        return tree[node] = update(node * 2, start, mid, idx, val) + update(node * 2 + 1, mid + 1, end, idx, val);

    }

    static int getCandyIdx(int node, int start, int end, int num) {    // num 번째 캔디 하나 꺼내기
        if(start == end){
            return start;
        }else{
            long leftCnt = tree[node * 2];
            int mid = (start + end) / 2;
            if (leftCnt >= num) {
                return getCandyIdx(node * 2, start, mid, num);
            }
            num -= leftCnt;
            return getCandyIdx(node * 2 + 1, mid + 1, end, num);
        }
    }
}