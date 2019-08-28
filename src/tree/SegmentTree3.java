package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5
 */
public class SegmentTree3 {
    static int[] arr;
    static int[] tree;
    static int N, M, K;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        tree = new int[N * 4];

        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
        }
        init(1, 1, N);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                // update
                update(1, 1, N, a, b);
            } else if (cmd == 2) {
                // query
                System.out.println(query(1, 1, N, a, b));
            }
        }
    }

    static int init(int node, int left, int right) {
        if(left == right) return tree[node] = arr[left];
        int mid = (left + right) / 2;
        return tree[node] = init(node * 2, left, mid) + init(node * 2 + 1, mid + 1, right);
    }

    static int update(int node, int left, int right, int idx, int val) {
        if (idx < left || right < idx) {
            return tree[node];
        }
        if (left == right) {
            return tree[node] = val;
        }
        int mid = (left + right) / 2;
        return tree[node] = update(node * 2, left, mid, idx, val) + update(node * 2 + 1, mid + 1, right, idx, val);
    }

    static int query(int node, int left, int right, int start, int end) {
        if (left > end || start > right) {
            return 0;
        }
        if (start <= left && right <= end) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, start, end) + query(node * 2 + 1, mid + 1, right, start, end);
    }
}
