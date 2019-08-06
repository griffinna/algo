package tree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IndexTree1 {
    static int[] arr = new int[100001];
    static int[] inv = new int[100001];
    static int[] tree = new int [100001];

    // (루트노트, 구하고자하는 범위, 현재범위)
    static int get(int node, int left, int right, int str, int end) {
        // 1. 구하고자 하는 구간과 하나도 겹치지 않음
        if(end < left || str > right) return 0;
        // 2. 구하고자 하는 구간과 완전히 포함
        if(str <= left && right <= end) return tree[node];
        // 3. 구하고자 하는 구간과 현재 구간이 겹침
        int mid = (str + end) / 2;
        return get(node * 2, left, right, str, mid) + get(node * 2 + 1, left, right, mid + 1, end);
    }

    // (루트노드, 업데이트할 리프노드, 현재 범위)
    static void update(int node, int num, int str, int end) {
        // 1. 구하고자 하는 구간과 하나도 겹치지 않음
        if(num < str || end < num) return;
        // 2. 구하고자 하는 구간과 완전히 포함
        if (str == end) {
            tree[node] = 1;
            return;
        }
        // 3. 구하고자 하는 구간과 현재 구간이 겹침
        int mid = (str + end) / 2;
        update(node * 2, num, str, mid);
        update(node * 2 + 1, num, mid + 1, end);
        tree[node]++;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("/Users/garam/gitRepository/algo/src/text/sample_input_01.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(tree, 0);
            for (int j = 0; j < N; j++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
