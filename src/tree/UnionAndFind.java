package tree;

public class UnionAndFind {

    static int[] parent = new int[1000];

    static int find(int n) {
        if (parent[n] == n) {
            return n;
        }else{
            return parent[n] = find(parent[n]);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        // 부모가 같지 않을 때 merge
        if (a != b) {
            if (a > b) {
                parent[a] = b;
            }else{
                parent[b] = a;
            }
        }
    }

    static boolean isSameParent(int a, int b) {
        a = find(a);
        b = find(b);
        return a == b;
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 8; i++) {
            parent[i] = i;
        }

        union(1, 3);
        System.out.printf("%d and %d has same parent? %s\n", 1, 3, isSameParent(1, 3));
        union(5, 3);
        System.out.printf("%d and %d has same parent? %s\n", 5, 3, isSameParent(5, 3));
        System.out.printf("%d and %d has same parent? %s\n", 2, 5, isSameParent(2, 5));


    }
}
