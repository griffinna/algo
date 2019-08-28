package tree;
/*



void debug() {
    int size = 2 * N - 1;
    int pow = 2;
    for (int i = 1; i <= size; i++) {
        printf("%d ", segment_tree[i]);
        if (i == (pow - 1)) {
            printf("\n");
            pow = pow * 2;
        }
    }

    printf("\n");
}


int main(int argc, char** argv) {
    initialize();

    for (int i = 1; i <= N; i++) {
        update(i, data[i]);
    }

    // Segment Tree 출력
    debug();

    // 최소값을 구하고자 하는 구간 입력
    int left = 2;
    int right = 8;
    int value = query(left, right, 1, N, 1);
    printf("Minimum between %d and %d : %d\n",
        left, right, value);

    return 0;
}
 */
public class SegmentTreeExample {

    static int MAX_TREE_SIZE = 10000;
    static int INFINITY = 9999999;
    static int data[] = {0, 6, 3, 7, 5, 2, 4, 8, 1};
    static int N = 8;
    static int[] segTree = new int[MAX_TREE_SIZE * 2];

    public static void main(String[] args) {
        initialize();
        for (int i = 1; i <= N; i++) {
            update(i, data[i]);
        }

        debug();

        // 최소, 최대 구하고자하는 구간 입력
        int left = 2;
        int right =  8;
        int val = query(left, right, 1, N, 1);
        System.out.printf("Minimum between %d and %d : %d\n", left, right, val);
    }

    static void initialize() {
        int size = 2 * N - 1;
        for (int i = 1; i <= size; i++) {
            segTree[i] = INFINITY;
        }
    }

    static void update(int idx, int val) {
        idx = idx + (N - 1);
        segTree[idx] = val;
        while (true) {
            idx = idx / 2;
            if(idx == 0) break;
            segTree[idx] = Math.min(segTree[idx * 2], segTree[idx * 2 + 1]);
        }
    }

    // left, right : 구하고자 하는 값의 범위
    // segment_left, segment_right : 현재 Segment의 구간
    // node_id : 현재 node의 index
    static int query(int left, int right, int seg_left, int seg_right, int idx) {
        // 관련없음
        if (seg_right < left || right < seg_left) {
            return INFINITY;
        }
        // 범위 내
        if (seg_left <= left && right <= seg_right) {
            return segTree[idx];
        }

        int mid = (seg_left + seg_right) / 2;
        int leftVal = query(left, right, seg_left, mid, idx * 2);
        int rightVal = query(left, right, mid + 1, seg_right, idx * 2 + 1);

        return Math.min(leftVal, rightVal);
    }

    static void debug() {
        int size = N * 2 - 1;
        int pow = 2;
        for (int i = 1; i <= size; i++) {
            System.out.printf("%d", segTree[i]);
            if (i == (pow - 1)) {
                System.out.println();
                pow = pow * 2;
            }
        }
        System.out.println();
    }
}
