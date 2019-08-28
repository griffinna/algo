package tree;

public class UnionFind {
    private static int[] parent = new int[1000001];

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        // 같은 부모를 갖고있지 않을때
        if (x != y) {
            if (x < y) {
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    private static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 8; i++) {
            parent[i] = i;
        }

        union(2, 3);
        union(4, 5);
        union(7, 8);
        union(1, 2);

        System.out.println("1 and 3 has same parent ? " + isSameParent(1, 3));
        System.out.println("4 and 8 has same parent ? " + isSameParent(4, 8));
        System.out.println("1 and 5 has same parent ? " + isSameParent(1, 5));

    }
}
