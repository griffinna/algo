package tree;

public class SegmentTree2 {

    static int[] tree;
    static int[] arr;

    public static void main(String[] args) {
//        [0, 38, 30, 8, 15, 15, 5, 3, 8, 7, 9, 6, 4, 1, 2, 1, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        arr = new int[]{0, 5, 3, 7, 9, 6, 4, 1, 2, 1};
        tree = new int[arr.length * 4];
        init(0, 1, 9);
    }
    static int init(int node, int left, int right) {
        if (left == right) {
            return tree[node] = arr[left];
        } else {
            int mid = (left + right) / 2;
            return tree[node] = init(node * 2, left, mid) + init(node * 2 + 1, mid + 1, right);
        }
    }

    // idx 번째 값을 n 으로 update
    static int update(int node, int left, int right, int idx, int n) {
        if (idx < left || idx < right) {
            return tree[node];
        }
        if (left == right) {
            tree[node] = n;
        }
        int mid = (left + right) / 2;
        return tree[node] = update(node * 2, left, mid, idx, n) + update(node * 2 + 1, mid + 1, right, idx, n);
    }

    // a ~ b 합
    static int query(int node, int left, int right, int a, int b) {
        if (b < left || a > right) {
            return 0;
        }
        if (a <= left && right <= b) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, a, b) + query(node * 2 + 1, mid + 1, right, a, b);
    }


}
