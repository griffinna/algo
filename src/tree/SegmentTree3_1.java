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
1 3 4 6
2 2 5
1 1 3 -2
2 2 5
 */
public class SegmentTree3_1 {
    static int[] arr;
    static int[] tree;
    static int N, M, K;
    static Tree t;
    static class Tree {
        int[] value;
        int[] lazy;

        public Tree(int n) {
            value = new int[n * 4];
            lazy = new int[n * 4];
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        t = new Tree(N);

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
                int c = Integer.parseInt(st.nextToken());
                update_range(1, 1, N, a, b, c);
            } else if (cmd == 2) {
                // query
                System.out.println(sum(1, 1, N, a, b));
            }
        }
    }

    static int init(int node, int left, int right) {
        if(left == right) return t.value[node] = arr[left];
        int mid = (left + right) / 2;
        return t.value[node] = init(node * 2, left, mid) + init(node * 2 + 1, mid + 1, right);
    }

    static void update_lazy(int node, int str, int end) {
        if (t.lazy[node] != 0) {
            t.value[node] += (end - str + 1) * t.lazy[node];
            if (str != end) {
                t.lazy[node * 2] += t.lazy[node];
                t.lazy[node * 2 + 1] += t.lazy[node];
            }
            t.lazy[node] = 0;
        }
    }

    static void update_range(int node, int left, int right, int str, int end, int diff) {

        update_lazy(node, left, right);

        if (str > right || end < left) {
            return;
        }
        if (str <= left && right <= end) {
//            t.value[node] += (end - str + 1) * diff;
            t.value[node] += (right - left + 1) * diff;
//            if (str != end) {
            if(right != left) {
                t.lazy[node * 2] += diff;
                t.lazy[node * 2 + 1] += diff;
            }
            return;
        }
        int mid = (left + right) / 2;
        update_range(node * 2, left, mid, str, end, diff);
        update_range(node * 2 + 1, mid + 1, right, str, end, diff);
        t.value[node] = t.value[node * 2] + t.value[node * 2 + 1];
    }

    static int sum(int node, int left, int right, int start, int end) {

        update_lazy(node, left, right);

        if (start > right || left > end) {
            return 0;
        }
        if (start <= left && right <= end) {
            return t.value[node];
        }
        int mid = (left + right) / 2;
        return sum(node * 2, left, mid, start, end) + sum(node * 2 + 1, mid + 1, right, start, end);
    }
}
