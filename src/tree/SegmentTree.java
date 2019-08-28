package tree;

import java.util.Arrays;

public class SegmentTree {

    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 9, 6, 4, 1, 2, 1};
        SegTree segTree = new SegTree(arr, arr.length);
        System.out.println(Arrays.toString(segTree.segmentArr));
    }

    static class SegTree {
        int [] segmentArr;

        public SegTree(int[] arr, int n) {
            int x = (int) Math.ceil(Math.log(n) / Math.log(2));
            int segmentSize = (int) (Math.pow(2, x) * 2 - 1);
            segmentArr = new int[segmentSize];

//            segmentArr = new int[n * 4];
            init(arr, 0, n - 1, 1);
        }

        // node 를 루트로 하는 서브트리를 초기화, 이 구간의 최소치를 반환
        private int init(int[] arr, int left, int right, int node) {
            if (left == right) {
                return segmentArr[node] = arr[left];
            }

            int mid = (left + right) / 2;
            segmentArr[node] += init(arr, left, mid, node * 2);
            segmentArr[node] += init(arr, mid + 1, right, node * 2 + 1);

            return segmentArr[node];
        }
    }
}
