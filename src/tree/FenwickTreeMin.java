package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
10 4
75
30
100
38
50
51
52
20
81
5
1 10
3 5
6 9
8 10
 */
public class FenwickTreeMin {
    private static int N, M;    // N : 숫자갯수 , M : 쿼리 수
    private static int arr[];
    private static int tree[];
    private static int tree2[];
    private static int MAX = 1000000001;
    private static void update(int idx, int val) {
        while (idx <= arr.length) {
            tree[idx] = Math.min(tree[idx], val);
            idx += idx & (-idx);
        }
    }

    private static void update2(int idx, int val) {
        while (idx > 0) {
            tree2[idx] = Math.min(tree2[idx], val);
            idx -= idx & (-idx);
        }
    }

    private static int query(int a, int b) {
        int min = MAX;
        int prev = a;
        int curr = prev + (prev & -prev);
        while (curr <= b) {
            min = Math.min(min, tree2[prev]);
            prev = curr;
            curr = prev + (prev & -prev);
        }

        min = Math.min(min, arr[prev]);
        prev = b;
        curr = prev - (prev & -prev);
        while (curr >= a) {
            min = Math.min(min, tree[prev]);
            prev = curr;
            curr = prev - (prev & -prev);
        }
        return min;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1]; // index 0 not used
        tree = new int[N + 1]; // index 0 not used
        tree2 = new int[N + 1]; // index 0 not used
        Arrays.fill(tree, MAX);
        Arrays.fill(tree2, MAX);
        for (int i = 1; i <=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            update(i, arr[i]);
            update2(i, arr[i]);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int str = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            System.out.println(query(str, end));
        }
    }
}
