package tree;
// find Sum
public class FenwickTree {

    int[] arr;
    public FenwickTree(int size){
        arr = new int[size + 1];
    }

    public int size() {
        return arr.length - 1;
    }

    private void update(int idx, int val) {
        assert idx > 0;
        while (idx <= arr.length) {
            arr[idx] += val;
            idx += idx & (-idx);
        }
    }

//    private void updateRange(int str, int end, int val) {
//        if (str <= end) {
//            update(str, val);
//            update(end + 1, -val);
//        }else{
//            update(1, val);
//            update(str + 1, -val);
//            update(end, val);
//        }
//    }

    private int findOne(int idx) {
        assert idx > 0;
        int sum = 0;
        while (idx > 0) {
            sum += arr[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    private int findRange(int str, int end) {
        assert str > 0 && end > 0 && str <= end;
        return findOne(end) - findOne(str - 1);
    }

    public static void main(String[] args) {
        // generator
        FenwickTree ft;
        FenwickTree ft2;

        int num = 6;
        int[] data = {0, 1, 2, 3, 4, 5, 6};
        ft = new FenwickTree(num);
        ft2 = new FenwickTree(data.length - 1);

        for (int i = 1; i < data.length; i++) {
            ft.update(i, data[i]);
            ft2.update(i, data[i]);
        }

        // sum 1,3
        int a = 1;
        int b = 5;

        int a2 = 3;
        int b2 = 6;

        System.out.printf("sum from %d to %d is %d%n", a, b, ft.findRange(a, b));
        System.out.printf("sum from %d to %d is %d%n", a2, b2, ft2.findRange(a2, b2));

    }

}
